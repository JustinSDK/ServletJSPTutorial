package cc.openhome;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
    private static Properties props;
    
    static {
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);
    }
    
    public static void main(String[] args) {
        try {
            Message message = createMessage(
                    "from@gmail.com", 
                    "to@gmail.com", "測試", "這是一封測試");
            Transport.send(message);  
            System.out.println("郵件傳送成功");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private static Message createMessage(
            String from, String to, String subject, String text)
                              throws MessagingException {
        Session session = Session.getInstance(props, new Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("yourname@gmail.com", "yourpassword");  
            }} 
        );  
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setText(text);
        
        return message;
    }
}
