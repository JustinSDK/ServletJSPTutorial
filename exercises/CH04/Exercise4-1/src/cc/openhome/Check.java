package cc.openhome;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/check")
public class Check extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Check</title>");
        out.println("</head>");
        out.println("<body>");

        String passwd = (String) request.getSession().getAttribute("passwd");

        if (passwd.equals(request.getParameter("passwd"))) {
            out.println("<h1>密碼正確</h1>");
        } else {
            out.println("<h1>密碼錯誤</h1>");
        }

        out.println("</body>");
        out.println("</html>");
    }

}
