package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/online.view")
public class Online extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Online</title>");
        out.println("</head>");
        out.println("<body>");

        Map<String, HttpSession> sessions = OnlineUser.sessions;
        out.printf("目前線上人數 %d 人<br><br>", sessions.size());
        sessions.values().forEach(session -> {
            User user = (User) session.getAttribute("user");

            out.printf("%s<br>", 
                    Instant.ofEpochMilli(session.getLastAccessedTime())
                           .atZone(ZoneId.of("Asia/Taipei"))
                           .toLocalDateTime());
            out.printf("%s<br><br>", user);
        });

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
