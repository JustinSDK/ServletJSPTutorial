package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class Compression extends HttpFilter {
    protected void doFilter(HttpServletRequest request, HttpServletResponse response,
           FilterChain chain) throws IOException, ServletException {

        String encodings = request.getHeader("Accept-Encoding");
        if (encodings != null && encodings.contains("gzip")) {
            CompressionWrapper responseWrapper = new CompressionWrapper(response); 
            responseWrapper.setHeader("Content-Encoding", "gzip");

            chain.doFilter(request, responseWrapper);

            responseWrapper.finish();
        }
        else {
            chain.doFilter(request, response);
        }
    }
}