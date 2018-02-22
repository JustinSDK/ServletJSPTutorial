package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/header")
public class Header extends HttpServlet {
    @Override
    protected void doGet(
                HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Show Headers</title>");
        out.print("</head>");
        out.print("<body>");

        /* 取得全部標頭並輸出至瀏覽器  */

        out.print("</body>");
        out.print("</html>");
    }
}