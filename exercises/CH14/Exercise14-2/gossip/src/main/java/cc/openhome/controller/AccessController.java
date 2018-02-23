package cc.openhome.controller;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cc.openhome.model.Account;
import cc.openhome.model.Message;
import cc.openhome.model.UserService;

@Controller
public class AccessController {
    @Value("${path.redirect.member}")
    private String REDIRECT_MEMBER_PATH;
    
    @Value("${path.redirect.index}")
    private String REDIRECT_INDEX_PATH;
    
    @Value("${path.index}")
    private String INDEX_PATH;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(
            @RequestParam(required=true) String username, 
            @RequestParam(required=true) String password,
            HttpServletRequest request) {

        Optional<Account> optionalAcct = userService.accountByName(username);

        if(optionalAcct.isPresent() && optionalAcct.get().isEnabled() && userService.login(username, password)) {
            request.getSession().setAttribute("login", username);
            return REDIRECT_MEMBER_PATH;
        } else {
            loginFailed(username, request);
            return INDEX_PATH;
        }
    }

    private void loginFailed(String username, HttpServletRequest request) {
        request.setAttribute("errors", Arrays.asList("登入失敗"));
        request.setAttribute("username", username);
        List<Message> newest = userService.newestMessages(10);
        request.setAttribute("newest", newest);
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) throws ServletException {
        session.invalidate();
        return REDIRECT_INDEX_PATH;
    }
}
