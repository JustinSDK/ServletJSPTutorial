package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {
         request.getSession().invalidate();
         response.sendRedirect("login.html");
    } 
}