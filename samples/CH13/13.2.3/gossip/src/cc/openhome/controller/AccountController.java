package cc.openhome.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            @Valid RegisterForm form,
            BindingResult bindingResult,
            Model model) {

        List<String> errors = toList(bindingResult);
        if(!form.getPassword().equals(form.getPassword2())) {
            errors.add("請再度確認密碼");
        }
        
        String path;
        if(errors.isEmpty()) {
            path = REGISTER_SUCCESS_PATH;
            
            Optional<Account> optionalAcct = userService.tryCreateUser(
                    form.getEmail(), form.getUsername(), form.getPassword());
            if(optionalAcct.isPresent()) {
                emailService.validationLink(optionalAcct.get());
            } else {
                emailService.failedRegistration(form.getUsername(), form.getEmail());
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
            @Valid ResetPasswordForm form,
            BindingResult bindingResult,
            @SessionAttribute(name = "token") String storedToken,
            Model model) {
        
        if(storedToken == null || !storedToken.equals(form.getToken())) {
            return REDIRECT_INDEX_PATH;
        }
        
        List<String> errors = toList(bindingResult);
        if(!form.getPassword().equals(form.getPassword2())) {
            errors.add("請再度確認密碼");
        }
        
        if(!errors.isEmpty()) {
            Optional<Account> optionalAcct = userService.accountByNameEmail(form.getName(), form.getEmail());
            model.addAttribute("errors", errors);
            model.addAttribute("acct", optionalAcct.get());
            return RESET_PASSWORD_FORM_PATH;
        } else {
            userService.resetPassword(form.getName(), form.getPassword());
            return RESET_PASSWORD_SUCCESS_PATH;
        }    
    }  
    
    private List<String> toList(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>(); 
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(err -> {
                errors.add(err.getDefaultMessage());
            });
        }
        return errors;
    }        
}
