package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/delete")
public class Delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                            throws ServletException, IOException {
        String id = request.getParameter("id");
        File file = new File();
        file.setId(Long.parseLong(id));
        FileService fileService = 
                (FileService) getServletContext().getAttribute("fileService");
        fileService.delete(file);
        response.sendRedirect("file.jsp");
    }
}
