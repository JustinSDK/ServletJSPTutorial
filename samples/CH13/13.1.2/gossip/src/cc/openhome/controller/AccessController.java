package cc.openhome.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cc.openhome.model.Message;
import cc.openhome.model.UserService;

@Controller
public class AccessController {
    private String REDIRECT_MEMBER_PATH = "/gossip/member";
    private String REDIRECT_INDEX_PATH = "/gossip";
    private String INDEX_PATH = "/WEB-INF/jsp/index.jsp";
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserService userService = (UserService) request.getServletContext().getAttribute("userService");
        Optional<String> optionalPasswd = userService.encryptedPassword(username, password);
        
        try {
            request.login(username, optionalPasswd.get());
            request.getSession().setAttribute("login", username);
            response.sendRedirect(REDIRECT_MEMBER_PATH);
        } catch(NoSuchElementException | ServletException e) {
            request.setAttribute("errors", Arrays.asList("登入失敗"));
            List<Message> newest = userService.newestMessages(10);
            request.setAttribute("newest", newest);
            request.getRequestDispatcher(INDEX_PATH)
                   .forward(request, response);
        }
    }
    
    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.logout(); 
        response.sendRedirect(REDIRECT_INDEX_PATH);
    }
}
