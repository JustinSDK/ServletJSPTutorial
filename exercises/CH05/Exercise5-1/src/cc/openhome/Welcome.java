package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/welcome.view")
public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                                  HttpServletResponse response) 
                             throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>歡迎</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>目前線上人數 %d 人</h1>", OnlineUser.sessions.size());
        if(session != null) {
            User user = (User) session.getAttribute("user");
            out.printf("<h1>歡迎：%s</h1>", user.name);
            out.println("<a href='logout'>登出</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }

}
