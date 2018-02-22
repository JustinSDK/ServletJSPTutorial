package cc.openhome;

import java.io.*;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location="c:/workspace")
@WebServlet("/uploads")
public class Uploads extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        
        /*  取得 Part 並進行檔案讀寫  */
    }
    
} 
