package cc.openhome;

import java.io.*;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/pet")
public class Pet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        out.printf("聯絡人：<a href='mailto:%s'>%s</a>%n", 
            request.getParameter("email"), 
            request.getParameter("user")
        );

        out.println("<br>喜愛的寵物類型");
        out.println("<ul>");

        Arrays.asList(request.getParameterValues("type"))
              .forEach(type -> out.printf("<li>%s</li>%n", type));

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}