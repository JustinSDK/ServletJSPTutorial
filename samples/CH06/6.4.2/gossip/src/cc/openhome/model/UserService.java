package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class UserService {
    private final String USERS;

    public UserService(String USERS) {
        this.USERS = USERS;
    }

    public void tryCreateUser(String email, String username, String password) throws IOException {
        Path userhome = Paths.get(USERS, username);

        if (Files.notExists(userhome)) {
            createUser(userhome, email, password);
        }
    }

    private void createUser(Path userhome, String email, String password) throws IOException {
        Files.createDirectories(userhome);

        int salt = (int) (Math.random() * 100);
        String encrypt = String.valueOf(salt + password.hashCode());

        Path profile = userhome.resolve("profile");
        try (BufferedWriter writer = Files.newBufferedWriter(profile)) {
            writer.write(String.format("%s\t%s\t%d", email, encrypt, salt));
        }
    }

    public boolean login(String username, String password) throws IOException {

        if (username != null && username.trim().length() != 0 && password != null) {
            Path userhome = Paths.get(USERS, username);
            return Files.exists(userhome) && isCorrectPassword(password, userhome);
        }
        return false;
    }

    private boolean isCorrectPassword(String password, Path userhome) throws IOException {
        Path profile = userhome.resolve("profile");
        try (BufferedReader reader = Files.newBufferedReader(profile)) {
            String[] data = reader.readLine().split("\t");
            int encrypt = Integer.parseInt(data[1]);
            int salt = Integer.parseInt(data[2]);
            return password.hashCode() + salt == encrypt;
        }
    }
    
    public List<Message> messages(String username) throws IOException {
        Path userhome = Paths.get(USERS, username);
          
        List<Message> messages = new ArrayList<>();
      
        try(DirectoryStream<Path> txts = 
                Files.newDirectoryStream(userhome, "*.txt")) {
          
            for(Path txt : txts) {
                String millis = txt.getFileName().toString().replace(".txt", "");
                String blabla = Files.readAllLines(txt).stream()
                        .collect(
                            Collectors.joining(System.lineSeparator())
                        );
                
                messages.add(new Message(username, Long.parseLong(millis), blabla));
            }
        }
        
        
        messages.sort(Comparator.comparing(Message::getMillis).reversed());

        return messages;
    }   
    
    public void addMessage(String username, String blabla) throws IOException {
        Path txt = Paths.get(
                      USERS, 
                      username, 
                      String.format("%s.txt", Instant.now().toEpochMilli())
                   );
        try(BufferedWriter writer = Files.newBufferedWriter(txt)) {
            writer.write(blabla);
        }
    }    
    
    public void deleteMessage(String username, String millis) throws IOException {
        Path txt = Paths.get(
                USERS, 
                username, 
                String.format("%s.txt", millis)
             );
        Files.delete(txt);
    }
    
}
