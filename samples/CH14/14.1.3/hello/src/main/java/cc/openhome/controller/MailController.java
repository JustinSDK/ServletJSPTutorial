package cc.openhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import cc.openhome.model.AccountDAO;

@Controller
public class MailController {  
    @Autowired
    private AccountDAO acctDAO;
  
    @RequestMapping("addr")
    public String addr(
            @RequestParam(required=true) String name,
            Model model) {
        model.addAttribute("addr", String.format("%s@openhome.cc", name));
        return "addr";
    }
    
    @RequestMapping("addr2")
    public String addr2(
            @RequestParam(required=true) String name,
            Model model) {
        model.addAttribute("addr", String.format("%s", 
            acctDAO.accountByUsername(name).get().getEmail())
        );
        return "addr";
    }    
}
