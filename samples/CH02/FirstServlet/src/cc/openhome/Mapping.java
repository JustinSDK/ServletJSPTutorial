package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mapping/*")
public class Mapping extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpServletMapping mapping = request.getHttpServletMapping();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Mapping Servlet</title>");
        out.print("</head>");
        out.print("<body>");
        out.printf("%s<br>", mapping.getMappingMatch());
        out.printf("%s<br>", mapping.getMatchValue());
        out.print(mapping.getPattern());
        out.print("</body>");
        out.print("</html>");
    }
}