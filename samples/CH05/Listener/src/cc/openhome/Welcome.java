package cc.openhome;

import java.io.*;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome.view")
public class Welcome extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                             throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>歡迎</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>目前線上人數 %d 人</h1>", OnlineUsers.counter);
        
        Optional.ofNullable(request.getSession(false))
                .ifPresent(session -> {
                    String user = (String) session.getAttribute("user");
                    out.printf("<h1>歡迎：%s </h1>", user);
                    out.println("<a href='logout'>登出</a>");
                });   
        
        out.println("</body>");
        out.println("</html>");
    }
}
