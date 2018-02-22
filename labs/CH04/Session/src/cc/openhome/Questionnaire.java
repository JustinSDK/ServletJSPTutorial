package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/questionnaire")
public class Questionnaire extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response) 
                      throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("</head>");
        out.println("<body>");

        String page = request.getParameter("page");
        out.println("<form action='questionnaire' method='post'>");

        if("page1".equals(page)) {          // 第一頁問卷
            page1(out);
        }
        else if("page2".equals(page)) {    // 第二頁問卷
            page2(request, out);
        }
        else if("finish".equals(page)) {    // 最後答案收集
            page3(request, out);
        }
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }


    private void page1(PrintWriter out) {
        out.println("問題一：<input type='text' name='p1q1'><br>");
        out.println("問題二：<input type='text' name='p1q2'><br>");
        out.println("<input type='submit' name='page' value='page2'>");
    } 

    private void page2(HttpServletRequest request, PrintWriter out) {
        String p1q1 = request.getParameter("p1q1");
        String p1q2 = request.getParameter("p1q2");
        out.println("問題三：<input type='text' name='p2q1'><br>");
        out.printf("<input type='hidden' name='p1q1' value='%s'>%n", p1q1);
        out.printf("<input type='hidden' name='p1q2' value='%s'>%n", p1q2);
        out.println("<input type='submit' name='page' value='finish'>");
    }

    private void page3(HttpServletRequest request, PrintWriter out) {
        out.println(request.getParameter("p1q1") + "<br>");
        out.println(request.getParameter("p1q2") + "<br>");
        out.println(request.getParameter("p2q1") + "<br>");
    }
    
}