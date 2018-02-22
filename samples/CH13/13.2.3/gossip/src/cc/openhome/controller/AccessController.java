package cc.openhome.controller;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

        Optional<String> optionalPasswd = userService.encryptedPassword(username, password);
        
        try {
            request.login(username, optionalPasswd.get());
            request.getSession().setAttribute("login", username);
            return REDIRECT_MEMBER_PATH;
        } catch(NoSuchElementException | ServletException e) {
            request.setAttribute("errors", Arrays.asList("登入失敗"));
            List<Message> newest = userService.newestMessages(10);
            request.setAttribute("newest", newest);
            return INDEX_PATH;
        }
    }
    
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout(); 
        return REDIRECT_INDEX_PATH;
    }
}
