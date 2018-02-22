package cc.openhome.controller;

import java.io.BufferedWriter;
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

@WebServlet("/new_message")
public class NewMessage extends HttpServlet {
    private final String USERS = "c:/workspace/Gossip/users";
    private final String LOGIN_PATH = "index.html";
    private final String MEMBER_PATH = "member";
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_PATH);
            return;
        }
            
        request.setCharacterEncoding("UTF-8");
        String blabla = request.getParameter("blabla");
        
        if(blabla == null || blabla.length() == 0) {
            response.sendRedirect(MEMBER_PATH);
            return;
        }        
       
        if(blabla.length() <= 140) {
            addMessage(getUsername(request), blabla);
            response.sendRedirect(MEMBER_PATH);
        }
        else {
            request.getRequestDispatcher(MEMBER_PATH).forward(request, response);
        }
    }

    private String getUsername(HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }
    
    private void addMessage(String username, String blabla) throws IOException {
        Path txt = Paths.get(
                      USERS, 
                      username, 
                      String.format("%s.txt", Instant.now().toEpochMilli())
                   );
        try(BufferedWriter writer = Files.newBufferedWriter(txt)) {
            writer.write(blabla);
        }
    }
}
