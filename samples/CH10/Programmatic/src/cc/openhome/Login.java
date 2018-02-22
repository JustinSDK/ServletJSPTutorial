package cc.openhome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        String user = request.getParameter("user");
        String passwd = request.getParameter("passwd");
        try {
            request.login(user, passwd);
            response.sendRedirect("user");
        } catch(ServletException ex) {
            response.sendRedirect("login.html");
        }
    } 
}
