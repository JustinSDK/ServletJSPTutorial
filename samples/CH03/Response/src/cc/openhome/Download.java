package cc.openhome;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class Download extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        String passwd = request.getParameter("passwd");
        if ("123456".equals(passwd)) {
            response.setContentType("application/pdf");
            
            try(InputStream in = getServletContext().getResourceAsStream("/WEB-INF/jdbc.pdf");
                OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
            }
        }
    }
}
