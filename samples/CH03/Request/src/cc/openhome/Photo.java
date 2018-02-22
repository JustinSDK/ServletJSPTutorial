package cc.openhome;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet("/photo") 
public class Photo extends HttpServlet { 
    private final Pattern fileNameRegex =
              Pattern.compile("filename=\"(.*)\"");
    
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        Part photo = request.getPart("photo");
        String filename = getSubmittedFileName(photo);
        write(photo, filename);
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
    
    private void write(Part photo, String filename) throws IOException, FileNotFoundException {
        try(InputStream in = photo.getInputStream();  
            OutputStream out = new FileOutputStream(
                    String.format("c:/workspace/%s", filename))) {
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
    }
}