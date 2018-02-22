package cc.openhome;

import java.io.*;
import java.util.Optional;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/counter")
public class Counter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        Integer count = Optional.ofNullable(request.getSession().getAttribute("count"))
                                .map(attr -> (Integer) attr + 1)
                                .orElse(0);
        request.getSession().setAttribute("count", count);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Count " + count + "</h1>");
        out.printf("<a href='%s'>遞增</a>%n", response.encodeURL("counter"));
        out.println("</body>");
        out.println("</html>");
    }
}