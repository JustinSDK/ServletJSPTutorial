package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/questionnaire")
public class Questionnaire extends HttpServlet {
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
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

        if("page1".equals(page)) {          
            page1(out);
        }
        else if("page2".equals(page)) {   
            page2(request, out);
        }
        else if("finish".equals(page)) {   
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
         // 完成程式碼
    }    

    private void page3(HttpServletRequest request, PrintWriter out) {
         // 完成程式碼
    }
}