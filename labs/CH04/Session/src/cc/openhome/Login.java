package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        
        String page;
        if("caterpillar".equals(name) && "123456".equals(passwd)) {
            processCookie(request, response);
            page = "user";
        }
        else {
            page = "login.html";
        }
        response.sendRedirect(page);
    }

    private void processCookie(HttpServletRequest request, HttpServletResponse response) {
        // 建立並加入 Cookie
    }
} 