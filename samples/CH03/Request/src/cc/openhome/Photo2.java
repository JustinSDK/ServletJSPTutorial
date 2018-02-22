package cc.openhome;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig(location="c:/workspace")
@WebServlet("/photo2") 
public class Photo2 extends HttpServlet { 
    private final Pattern fileNameRegex =
              Pattern.compile("filename=\"(.*)\"");
    
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part photo = request.getPart("photo");
        String filename = getSubmittedFileName(photo);
        photo.write(filename);
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
}