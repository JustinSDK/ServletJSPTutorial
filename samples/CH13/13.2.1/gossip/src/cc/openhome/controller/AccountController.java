package cc.openhome.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import cc.openhome.model.Account;
import cc.openhome.model.EmailService;
import cc.openhome.model.UserService;

@Controller
@SessionAttributes("token")
public class AccountController {
    @Value("${path.redirect.index}")
    private String REDIRECT_INDEX_PATH;
    
    @Value("${path.register_success}")
    private String REGISTER_SUCCESS_PATH;
    
    @Value("${path.register_form}")
    private String REGISTER_FORM_PATH;
    
    @Value("${path.verify}")
    private String VERIFY_PATH;

    @Value("${path.forgot}")
    private String FORGOT_PATH;
    
    @Value("${path.reset_password_form}")
    private String RESET_PASSWORD_FORM_PATH;
    
    @Value("${path.reset_password_success}")
    private String RESET_PASSWORD_SUCCESS_PATH;

    private final Pattern emailRegex = Pattern.compile(
            "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
    private final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;    
        
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerForm() {
        return REGISTER_FORM_PATH;
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(
            @RequestParam(required=true) String email,
            @RequestParam(required=true) String username,
            @RequestParam(required=true) String password,
            @RequestParam(required=true) String password2,
            Model model) {

        List<String> errors = new ArrayList<>(); 
        if (!validateEmail(email)) {
            errors.add("未填寫郵件或格式不正確");
        }
        if(!validateUsername(username)) {
            errors.add("未填寫使用者名稱或格式不正確");
        }
        if (!validatePassword(password, password2)) {
            errors.add("請確認密碼符合格式並再度確認密碼");
        }
        
        String path;
        if(errors.isEmpty()) {
            path = REGISTER_SUCCESS_PATH;
            
            Optional<Account> optionalAcct = userService.tryCreateUser(email, username, password);
            if(optionalAcct.isPresent()) {
                emailService.validationLink(optionalAcct.get());
            } else {
                emailService.failedRegistration(username, email);
            }
        } else {
            path = REGISTER_FORM_PATH;
            model.addAttribute("errors", errors);
        }

        return path;
    }    
    
    @RequestMapping("verify")
    public String verify(
            @RequestParam(required=true) String email, 
            @RequestParam(required=true) String token, 
            Model model)  {
        model.addAttribute("acct", userService.verify(email, token));
        return VERIFY_PATH;
    }
    
    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public String forgot(
            @RequestParam(required=true) String name,
            @RequestParam(required=true) String email,
            Model model) {
        
        Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
        
        if(optionalAcct.isPresent()) {
            emailService.passwordResetLink(optionalAcct.get());
        }
        
        model.addAttribute("email", email);
        return FORGOT_PATH;
    }
    
    @RequestMapping(value = "reset_password", method = RequestMethod.GET)
    public String resetPasswordForm(
            @RequestParam(required=true) String name,
            @RequestParam(required=true) String email,
            @RequestParam(required=true) String token,
            Model model) {

        Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
        
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            if(acct.getPassword().equals(token)) {
                model.addAttribute("acct", acct);
                model.addAttribute("token", token);
                return RESET_PASSWORD_FORM_PATH;
            }
        }
        return REDIRECT_INDEX_PATH;
    }
    
    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    public String resetPassword(
            @RequestParam(required=true) String token,
            @RequestParam(required=true) String name,
            @RequestParam(required=true) String email,
            @RequestParam(required=true) String password,
            @RequestParam(required=true) String password2,
            @SessionAttribute(name = "token") String storedToken,
            Model model) {
        
        if(storedToken == null || !storedToken.equals(token)) {
            return REDIRECT_INDEX_PATH;
        }
         
        if (!validatePassword(password, password2)) {
            Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
            model.addAttribute("errors", Arrays.asList("請確認密碼符合格式並再度確認密碼"));
            model.addAttribute("acct", optionalAcct.get());
            return RESET_PASSWORD_FORM_PATH;
        } else {
            userService.resetPassword(name, password);
            return RESET_PASSWORD_SUCCESS_PATH;
        }    
    }
    
    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }
    
    private boolean validateUsername(String username) {
        return username != null && usernameRegex.matcher(username).find();
    }

    private boolean validatePassword(String password, String password2) {
        return password != null && 
               passwdRegex.matcher(password).find() && 
               password.equals(password2);
    }    
}
