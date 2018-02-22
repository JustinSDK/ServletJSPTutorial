package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebFilter("/*")
public class TimeIt extends HttpFilter {
    @Override
    protected void doFilter(
         HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
         long begin = current();
         
         chain.doFilter(request, response);
         
         getServletContext().log(
             String.format("Request process in %d milliseconds", current() - begin)
         );
    }
    
    private long current() {
        return System.currentTimeMillis();
    }
}