package cc.openhome.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
    urlPatterns={"/login"}, 
    initParams={
        @WebInitParam(name = "SUCCESS_PATH", value = "member"),
        @WebInitParam(name = "ERROR_PATH", value = "/WEB-INF/jsp/index.jsp")
    }
)
public class Login extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserService userService = (UserService) getServletContext().getAttribute("userService");

        if(userService.login(username, password)) {
            if(request.getSession(false) != null) {
                request.changeSessionId();
            }
            request.getSession().setAttribute("login", username);
            response.sendRedirect(getInitParameter("SUCCESS_PATH"));
        } else {
            request.setAttribute("errors", Arrays.asList("登入失敗"));
            request.getRequestDispatcher(getInitParameter("ERROR_PATH"))
                   .forward(request, response);
        }
        
    }
}
