package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/some")
public class Some extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Some do one...");
        RequestDispatcher dispatcher = request.getRequestDispatcher("other");
        dispatcher.include(request, response);
        out.println("Some do two...");
    } 
}