package cc.openhome;

import java.util.Date;
import java.io.*;
import java.time.Instant;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
@WebServlet("/upload")
public class Upload extends HttpServlet {
    private final Pattern fileNameRegex =
            Pattern.compile("filename=\"(.*)\"");
  
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                     throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
       Part part = request.getPart("file");
       String filename = getSubmittedFileName(part);
       
        byte[] bytes = getBytes(part);
        
        File file = new File(); 
        file.setFilename(filename);
        file.setBytes(bytes);
        file.setSavedTime(Instant.now().toEpochMilli());
        
        FileService service = (FileService) 
                getServletContext().getAttribute("fileService");
        service.save(file);
        
        response.sendRedirect("file.jsp");
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        Matcher matcher = fileNameRegex.matcher(header);
        matcher.find();
        
        String filename =  matcher.group(1);
        if(filename.contains("\\")) {
            return filename.substring(filename.lastIndexOf("\\") + 1); 
        }
        return filename;
    }
    
    private byte[] getBytes(Part part) throws IOException {
        try(InputStream in = part.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }
}
