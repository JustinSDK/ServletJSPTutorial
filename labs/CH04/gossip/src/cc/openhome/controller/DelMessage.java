package cc.openhome.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del_message")
public class DelMessage extends HttpServlet {
    private final String USERS = "c:/workspace/Gossip/users";
    private final String LOGIN_PATH = "index.html";
    private final String MEMBER_PATH = "member";
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_PATH);
            return;
        }
        
        // 刪除訊息
    }
    
    private String getUsername(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("login");
    }
    
    private void deleteMessage(String username, String millis) throws IOException {
        Path txt = Paths.get(
                USERS, 
                username, 
                String.format("%s.txt", millis)
             );
        Files.delete(txt);
    }


    
    
}
