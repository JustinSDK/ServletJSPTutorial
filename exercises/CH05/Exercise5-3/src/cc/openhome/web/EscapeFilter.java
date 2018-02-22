package cc.openhome.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(
        urlPatterns = { "/new_message" }, 
        initParams = { 
                @WebInitParam(name = "CODE_LIST", value = "/WEB-INF/codelist.txt")
        })
public class EscapeFilter extends HttpFilter {
    private Map<String, String> escapeMap;

    public void init() throws ServletException {
        String codeList = getInitParameter("CODE_LIST");
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(getServletContext().getResourceAsStream(codeList)))) {
            String input = null;
            escapeMap = new HashMap<>();
            while ((input = reader.readLine()) != null) {
                String[] tokens = input.split("\t");
                escapeMap.put(tokens[0], tokens[1]);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
	public void doFilter(ServletRequest request, 
	                     ServletResponse response, 
	                     FilterChain chain) 
	                          throws IOException, ServletException {
	    
        HttpServletRequest requestWrapper = 
            new EscapeWrapper((HttpServletRequest) request, escapeMap);
        chain.doFilter(requestWrapper, response);
	}
}
