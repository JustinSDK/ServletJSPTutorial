package cc.openhome.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.openhome.model.Message;
import cc.openhome.model.UserService;


@Controller
public class DisplayController {
    @Value("${path.index}")
    private String INDEX_PATH;
    
    @Value("${path.user}")
    private String USER_PATH;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/")
    public String index(Model model) {
        List<Message> newest = userService.newestMessages(10);
        model.addAttribute("newest", newest);
        return INDEX_PATH;
    }
    
    @RequestMapping("user/{username}")
    public String user(
            @PathVariable("username") String username,
            Model model) {

        model.addAttribute("username", username);
        if(userService.userExisted(username)) {
            List<Message> messages = userService.messages(username);
            model.addAttribute("messages", messages);
        } else {
            model.addAttribute("errors", Arrays.asList(String.format("%s 還沒有發表訊息", username)));
        }
        return USER_PATH;
    }
}
