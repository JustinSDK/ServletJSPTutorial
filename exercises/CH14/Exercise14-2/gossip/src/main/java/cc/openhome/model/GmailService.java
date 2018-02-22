package cc.openhome.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component; 

@Component
public class GmailService implements EmailService {
    private final String mailUser;
    private final JavaMailSenderImpl mailSender;
    
    public GmailService(
            @Value("${mail.user}") String mailUser, 
            @Value("${mail.password}") String mailPassword) {
        
        this.mailUser = mailUser;
        
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(mailUser);
        mailSender.setPassword(mailPassword);
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        mailSender.setJavaMailProperties(props);
        
    }

    @Override
    public void validationLink(Account acct) {
        String link = String.format(
            "http://localhost:8080/verify?email=%s&token=%s", 
            acct.getEmail(), acct.getPassword()
        );
        
        String anchor = String.format("<a href='%s'>驗證郵件</a>", link);
        
        String html = String.format("請按 %s 啟用帳戶或複製鏈結至網址列：<br><br> %s", anchor, link);
        
        sendTo(acct.getEmail(), "Gossip 註冊結果", html);
    }

    @Override
    public void failedRegistration(String acctName, String acctEmail) {
        sendTo(acctEmail, "Gossip 註冊結果", 
                String.format("帳戶申請失敗，使用者名稱 %s 或郵件 %s 已存在！", 
                                     acctName, acctEmail)
         );
    }

    @Override
    public void passwordResetLink(Account acct) {
        String link = String.format(
            "http://localhost:8080/reset_password?name=%s&email=%s&token=%s", 
            acct.getName(), acct.getEmail(), acct.getPassword()
        );
        
        String anchor = String.format("<a href='%s'>重設密碼</a>", link);
        
        String html = String.format("請按 %s 或複製鏈結至網址列：<br><br> %s", anchor, link);
        
        
        sendTo(acct.getEmail(), "Gossip 重設密碼", html);
    }    
    
    
    private void sendTo(String email, String subject, String content) {
        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, false, "utf-8");
            mimeMsg.setContent(content, "text/html; charset=UTF-8");
            helper.setFrom(mailUser);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            
            mailSender.send(mimeMsg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
