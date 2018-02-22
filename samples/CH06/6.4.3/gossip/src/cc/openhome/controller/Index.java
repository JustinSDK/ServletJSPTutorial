package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class Index extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
               .forward(request, response);
    }
}
