package cc.openhome;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager")
@ServletSecurity(
    value=@HttpConstraint(rolesAllowed = {"admin", "manager"}), 
    httpMethodConstraints = {
        @HttpMethodConstraint(
            value = "GET", rolesAllowed = {"admin", "manager"}
        ),
        @HttpMethodConstraint(
            value = "POST", rolesAllowed = {"admin", "manager"}
        )
    }
)
public class Manager extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("只有 admin 與 manager 才看得到");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("只有 admin 與 manager 才看得到");
    }
}