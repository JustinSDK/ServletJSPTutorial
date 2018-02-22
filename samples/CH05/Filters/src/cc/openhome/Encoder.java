package cc.openhome;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class Encoder extends HttpFilter {
	public void doFilter(HttpServletRequest request, 
	    HttpServletResponse response, FilterChain chain) 
	                          throws IOException, ServletException {
        chain.doFilter(new EncoderWrapper(request), response);
	}
}
