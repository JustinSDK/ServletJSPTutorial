package cc.openhome.view;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_error.view")
public class RegisterError extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                  throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>表單填寫錯誤</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>表單填寫錯誤</h1>");
        out.println("<ul style='color: rgb(255, 0, 0);'>");
        
        List<String> errors = (List<String>) request.getAttribute("errors");
        errors.forEach(error -> out.printf("<li>%s</li>", error));
        
        out.println("</ul>");
        out.println("<a href='register.html'>返回註冊表單</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
