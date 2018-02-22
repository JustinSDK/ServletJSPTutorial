package cc.openhome.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
    private final String USERS = "c:/workspace/gossip/users";
    private final String SUCCESS_PATH = "register_success.view";
    private final String ERROR_PATH = "register_error.view";
    
    private final Pattern emailRegex = Pattern.compile(
        "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");

    private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
    
    private final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {

          /* 實作註冊流程 */
        
    }

    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }
    
    private boolean validateUsername(String username) {
        return username != null && usernameRegex.matcher(username).find();
    }

    
    private boolean validatePassword(String password, String password2) {
        return password != null && 
               passwdRegex.matcher(password).find() && 
               password.equals(password2);
    }

    private void tryCreateUser(
           String email, String username, String password) throws IOException {
        Path userhome = Paths.get(USERS, username);
        if(Files.notExists(userhome)) {
            createUser(userhome, email, password);
        }
    }

    private void createUser(Path userhome, String email, String password) throws IOException {
        Files.createDirectories(userhome);
        
        int salt = (int) (Math.random() * 100);
        String encrypt = String.valueOf(salt + password.hashCode());
        
        Path profile = userhome.resolve("profile");
        try(BufferedWriter writer = Files.newBufferedWriter(profile)) {
            writer.write(String.format("%s\t%s\t%d", email, encrypt, salt));
        }
    }
}
