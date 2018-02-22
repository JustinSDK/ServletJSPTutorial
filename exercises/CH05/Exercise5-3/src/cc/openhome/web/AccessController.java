package cc.openhome.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
    urlPatterns = {
        "/member", "/member.view", 
        "/new_message", "/del_message", 
        "/logout"
    }, 
    initParams = {
        @WebInitParam(name = "LOGIN_PATH", value = "index.html")
    }
)
public class AccessController extends HttpFilter {
    private String LOGIN_PATH;
    
    public void init() throws ServletException {
        this.LOGIN_PATH = getInitParameter("LOGIN_PATH");
    }

    public void doFilter(HttpServletRequest request,
                     HttpServletResponse response, FilterChain chain)
                        throws IOException, ServletException {

        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_PATH);
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
