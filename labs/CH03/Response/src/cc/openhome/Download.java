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
            
            /* 步驟 1 到 4 */
        }
    }
}
