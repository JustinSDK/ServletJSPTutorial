package cc.openhome;

import java.net.URLEncoder;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/download")
public class Download extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                             throws ServletException, IOException {
        FileService fileService = 
                (FileService) getServletContext().getAttribute("fileService");
        
        String id = request.getParameter("id");
        
        File file = new File();
        file.setId(Long.parseLong(id));
        file = fileService.getFile(file);
        
        String filename = fileName(request, file);
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", 
                "attachment; filename=\"" + filename + "\"");

        OutputStream out = response.getOutputStream();
        out.write(file.getBytes());
    }

    private String fileName(HttpServletRequest request, File file) 
                      throws UnsupportedEncodingException {
        String agent = request.getHeader("User-Agent");
        if(agent.contains("MSIE") || agent.contains("rv:")) {
            return URLEncoder.encode(file.getFilename(), "UTF-8");
        }
        return new String(file.getFilename().getBytes("UTF-8"), "ISO-8859-1");
    }
}
