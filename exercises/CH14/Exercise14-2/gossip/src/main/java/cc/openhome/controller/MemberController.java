package cc.openhome.controller;

import java.util.List;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import cc.openhome.model.Message;
import cc.openhome.model.UserService;

@Controller
public class MemberController {
    @Value("${path.member}")
    private String MEMBER_PATH;
    
    @Value("${path.redirect.member}")
    private String REDIRECT_MEMBER_PATH;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PolicyFactory policy;
    
    @RequestMapping("member")
    public String member(
            @SessionAttribute("login") String username, 
            Model model) {
        List<Message> messages = userService.messages(username);
        model.addAttribute("messages", messages);
        return MEMBER_PATH;
    }
    
    @RequestMapping(value = "new_message", method = RequestMethod.POST)
    protected String newMessage(
            @RequestParam(required=true) String blabla, 
            @SessionAttribute("login") String username, 
            Model model)  {
        
        if(blabla.length() == 0) {
            return REDIRECT_MEMBER_PATH;
        }        
       
        blabla = policy.sanitize(blabla);
        if(blabla.length() <= 140) {
            userService.addMessage(username, blabla);
            return REDIRECT_MEMBER_PATH;
        }
        else {
            model.addAttribute("blabla", blabla);
            model.addAttribute("messages", userService.messages(username));
            return MEMBER_PATH;
        }
    }  
    
    @RequestMapping(value = "del_message", method = RequestMethod.POST)
    protected String delMessage(
            @RequestParam(required=true) String millis, 
            @SessionAttribute("login") String username) {
        
        if(millis != null) {
            userService.deleteMessage(username, millis);
        }
        return REDIRECT_MEMBER_PATH;
    }        
}
