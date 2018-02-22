package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member.view")
public class MemberView extends HttpServlet {
    private final String LOGIN_PATH = "index.html";
    
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }
   
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_PATH);
            return;
        }
        
        String username = getUsername(request);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Gossip 微網誌</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='leftPanel'>");
        out.println("<img src='images/caterpillar.jpg' alt='Gossip 微網誌' /><br><br>");
        out.printf("<a href='logout'>登出 %s</a>", username);
        out.println("</div>");
        out.println("<form method='post' action='new_message'>");
        out.println("分享新鮮事...<br>");
        
        // 訊息回填欄位
        
        out.printf("<textarea cols='60' rows='4' name='blabla'>%s</textarea><br>", preBlabla);
        out.println("<button type='submit'>送出</button>");
        out.println("</form>");
        out.println("<table border='0' cellpadding='2' cellspacing='2'>");
        out.println("<thead>");
        out.println("<tr><th><hr></th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // 逐一顯示訊息

        out.println("</tbody>");
        out.println("</table>");
        out.println("<hr>");
        out.println("</body>");
        out.println("</html>");
    }

    private String getUsername(HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }
}
