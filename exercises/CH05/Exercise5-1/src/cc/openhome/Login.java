package cc.openhome;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
    private Map<String, String> users = new HashMap<String, String>() {{
        put("caterpillar", "123456");
        put("momor", "98765");
        put("irene", "13579");
    }};
    
    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
                             throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");

        String page = "form.html";
       if(users.containsKey(name) && users.get(name).equals(passwd)) {
           request.getSession().setAttribute("user", 
                   new User(name, request.getRemoteAddr(), request.getHeader("user-agent")));
           page = "welcome.view";
       }
       response.sendRedirect(page);
    } 
}
