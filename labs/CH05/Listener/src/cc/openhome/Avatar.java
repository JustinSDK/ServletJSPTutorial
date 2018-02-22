package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/avatar")
public class Avatar extends HttpServlet {
    // 讀取屬性

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        getServletContext().getResourcePaths(AVATAR_DIR)
                           .forEach(avatar -> {
                               out.printf("<img src='%s'>%n", avatar.replaceFirst("/", ""));
                           });

        out.println("</body>");
        out.println("</html>");
    }
}