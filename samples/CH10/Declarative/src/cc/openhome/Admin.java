package cc.openhome;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
@ServletSecurity(
    @HttpConstraint(rolesAllowed = {"admin"})
)
public class Admin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                         throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("只有 admin 才看得到");
    }
}