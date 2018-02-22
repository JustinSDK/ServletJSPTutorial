package cc.openhome;

import java.io.*;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/user")
public class User extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {

        HttpSession session = request.getSession();
        Optional<Object> token = 
                Optional.ofNullable(session.getAttribute("login"));

        if(token.isPresent()) {
            request.getRequestDispatcher("user.view")
                   .forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }
} 