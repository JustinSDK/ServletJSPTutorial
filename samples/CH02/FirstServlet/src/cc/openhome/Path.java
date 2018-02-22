package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/*")
public class Path extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Path Servlet</title>");
        out.print("</head>");
        out.print("<body>");
        out.printf("%s<br>", request.getRequestURI());
        out.printf("%s<br>", request.getContextPath());
        out.printf("%s<br>", request.getServletPath());
        out.print(request.getPathInfo());
        out.print("</body>");
        out.print("</html>");
    }
}