package cc.openhome.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDAOFileImpl implements MessageDAO {
    private final String USERS;
    
    public MessageDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }
    
    @Override
    public List<Message> messagesBy(String username) {
        try {
            return messages(username);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<Message> messages(String username) throws IOException {
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

        return messages;
    }       
    
    @Override
    public void createMessage(Message message) {
        try {
            addMessage(message);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private void addMessage(Message message) throws IOException {
        Path txt = Paths.get(
                      USERS, 
                      message.getUsername(), 
                      String.format("%s.txt", message.getMillis())
                   );
        try(BufferedWriter writer = Files.newBufferedWriter(txt)) {
            writer.write(message.getBlabla());
        }
    }       

    @Override
    public void deleteMessageBy(String username, String millis) {
        try {
            deleteMessage(username, millis);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
