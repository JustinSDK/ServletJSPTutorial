package cc.openhome;

import java.io.*;
import java.nio.file.Path;

import javax.servlet.*;
import javax.servlet.http.*;

public class Avatar2 extends HttpServlet {
    private final Path AVATAR_DIR;

    public Avatar2(Path AVATAR_DIR) {
        this.AVATAR_DIR = AVATAR_DIR;
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        String path = String.format("/%s", AVATAR_DIR.getFileName());
        getServletContext().getResourcePaths(path)
                           .forEach(avatar -> {
                               out.printf("<img src='%s'>%n", avatar.replaceFirst("/", ""));
                           });

        out.println("</body>");
        out.println("</html>");
    }
}