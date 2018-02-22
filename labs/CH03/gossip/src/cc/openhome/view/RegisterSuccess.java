package cc.openhome.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register_success.view")
public class RegisterSuccess extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>成功送出表單</title>");
        out.println("</head>");
        out.println("<body>");

        /* 實作程式 */
        
        out.println("<a href='index.html'>回首頁</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
