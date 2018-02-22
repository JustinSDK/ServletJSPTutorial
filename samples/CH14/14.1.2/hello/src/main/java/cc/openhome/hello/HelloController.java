package cc.openhome.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {    
    @RequestMapping("hello")
    @ResponseBody
    public String hello(
            @RequestParam(required=true) String name) {

        return String.format("哈囉！%s！", name);
    }
}
