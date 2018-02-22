package cc.openhome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @Autowired
    private HelloModel helloModel;
    
    @RequestMapping("hello")
    public String hello(
            @RequestParam(required=true) String user, Model model) {
        String message = helloModel.doHello(user);
        model.addAttribute("message", message);
        return "hello";
    }
}
