package cc.openhome.model;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final AccountDAO acctDAO;
    private final MessageDAO messageDAO;

    public UserService(AccountDAO acctDAO, MessageDAO messageDAO) {
        this.acctDAO = acctDAO;
        this.messageDAO = messageDAO;
    }

    public void tryCreateUser(String email, String username, String password)  {
        if(!acctDAO.accountBy(username).isPresent()) {
            createUser(username, email, password);
        }
    }

    private void createUser(String username, String email, String password) {
        int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        acctDAO.createAccount(
                new Account(username, email, 
                        String.valueOf(encrypt), String.valueOf(salt)));
    }

    public boolean login(String username, String password) {
        if(username == null || username.trim().length() == 0) {
            return false;
        }
        
        Optional<Account> optionalAcct = acctDAO.accountBy(username);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            int encrypt = Integer.parseInt(acct.getPassword());
            int salt = Integer.parseInt(acct.getSalt());
            return password.hashCode() + salt == encrypt;
        }
        return false;
    }
    
    public List<Message> messages(String username) {
        List<Message> messages = messageDAO.messagesBy(username);
        messages.sort(Comparator.comparing(Message::getMillis).reversed());
        return messages;
    }   
    
    public void addMessage(String username, String blabla) {
        messageDAO.createMessage(
                new Message(
                        username, Instant.now().toEpochMilli(), blabla));
    }    
    
    public void deleteMessage(String username, String millis) {
        messageDAO.deleteMessageBy(username, millis);
    }
    
    public boolean exist(String username) {
        return acctDAO.accountBy(username).isPresent();
    }
}
