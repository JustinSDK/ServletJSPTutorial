package cc.openhome.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.openhome.model.Account;
import cc.openhome.model.EmailService;
import cc.openhome.model.UserService;

@Controller
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
    public void registerForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER_FORM_PATH)
               .forward(request, response);
    }
    
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

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
            request.setAttribute("errors", errors);
        }

        request.getRequestDispatcher(path).forward(request, response);
    }    
    
    @RequestMapping("verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        request.setAttribute("acct", userService.verify(email, token));
        request.getRequestDispatcher(VERIFY_PATH).forward(request, response);
    }
    
    @RequestMapping(value = "forgot", method = RequestMethod.POST)
    public void forgot(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
        
        if(optionalAcct.isPresent()) {
            emailService.passwordResetLink(optionalAcct.get());
        }
        
        request.setAttribute("email", email);
        request.getRequestDispatcher(FORGOT_PATH)
               .forward(request, response);
    }
    
    @RequestMapping(value = "reset_password", method = RequestMethod.GET)
    public void resetPasswordForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
        
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            if(acct.getPassword().equals(token)) {
                request.setAttribute("acct", acct);
                request.getSession().setAttribute("token", token);
                request.getRequestDispatcher(RESET_PASSWORD_FORM_PATH)
                       .forward(request, response);
                return;
            }
        }
        response.sendRedirect(REDIRECT_INDEX_PATH);
    }
    
    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String storedToken = (String) request.getSession().getAttribute("token");
        if(storedToken == null || !storedToken.equals(token)) {
            response.sendRedirect("/gossip");
            return;
        }
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
         
        if (!validatePassword(password, password2)) {
            Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
            request.setAttribute("errors", Arrays.asList("請確認密碼符合格式並再度確認密碼"));
            request.setAttribute("acct", optionalAcct.get());
            request.getRequestDispatcher(RESET_PASSWORD_FORM_PATH)
                   .forward(request, response);
        } else {
            userService.resetPassword(name, password);
            request.getRequestDispatcher(RESET_PASSWORD_SUCCESS_PATH)
                   .forward(request, response);
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
