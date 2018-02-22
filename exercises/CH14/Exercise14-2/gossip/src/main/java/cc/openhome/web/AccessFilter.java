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
        "/member", 
        "/new_message", "/del_message", 
        "/logout"
    }, 
    initParams = {
        @WebInitParam(name = "LOGIN_PATH", value = "/gossip")
    }
)
public class AccessFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request,
                     HttpServletResponse response, FilterChain chain)
                        throws IOException, ServletException {

        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(getInitParameter("LOGIN_PATH"));
        }
        else {
            chain.doFilter(request, response);
        }
    }
}
