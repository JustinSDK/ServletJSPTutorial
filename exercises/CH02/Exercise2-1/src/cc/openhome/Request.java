package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request")
public class Request extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Exercise 2.1</title>");
        out.print("</head>");
        out.print("<body>");
        out.printf("<h1>Now Time: %s</h1>", LocalDateTime.now());
        out.printf("<h1>From: %s</h1>", request.getRemoteAddr());
        out.printf("<h1>Query String: %s</h1>", request.getQueryString());
        out.print("</body>");
        out.print("</html>");
    }
}
