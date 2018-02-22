package cc.openhome;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.http.Part;

import javax.mail.*;
import javax.mail.internet.*;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@MultipartConfig
@WebServlet(
    urlPatterns={"/mail"},
    initParams={
        @WebInitParam(name = "host", value = "smtp.gmail.com"),
        @WebInitParam(name = "port", value = "587"),
        @WebInitParam(name = "username", value = "yourname@gmail.com"),
        @WebInitParam(name = "password", value = "yourpassword")
    }
)
public class Mail extends HttpServlet {
    private final Pattern fileNameRegex =
            Pattern.compile("filename=\"(.*)\"");    
    
    private final String html = 
    "<html>" + 
    "    <head>" +
    "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
    "    </head>" +
    "    <body>" +
    "    <img" +
    "        style='border: 3px solid ; font-weight: bold;'" +
    "            src='cid:#filename'" +
    "            hspace='3' vspace='3'> <br>" +
    "        #text" +
    "    </body>" +
    "</html>";
    
    private String host;
    private String port;
    private String username;
    private String password;
    private Properties props;

    @Override
    public void init() throws ServletException {
        host = getServletConfig().getInitParameter("host");
        port = getServletConfig().getInitParameter("port");
        username = getServletConfig().getInitParameter("username");
        password = getServletConfig().getInitParameter("password");

        props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");
        Part part = request.getPart("image");

        try {
            Message message = createMessage(from, to, subject, text, part);
            Transport.send(message);
            response.getWriter().println("郵件傳送成功");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private Message createMessage(String from, String to, String subject, String text, Part part)
            throws MessagingException, AddressException, IOException {

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setContent(multiPart(text, part));

        return message;
    }

    private Multipart multiPart(String text, Part part)
            throws MessagingException, UnsupportedEncodingException, IOException {
        String filename = getSubmittedFileName(part);
        
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html.replace("#filename", filename).replace("#text", text), "text/html;charset=UTF-8");

        MimeBodyPart filePart = new MimeBodyPart();
        filePart.setFileName(MimeUtility.encodeText(filename, "UTF-8", "B"));
        filePart.setHeader("Content-ID", "<" + filename + ">");        
        filePart.setContent(getBytes(part), part.getContentType());
        
        Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(htmlPart);
        multiPart.addBodyPart(filePart);
        return multiPart;
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        Matcher matcher = fileNameRegex.matcher(header);
        matcher.find();

        String filename = matcher.group(1);
        if (filename.contains("\\")) {
            return filename.substring(filename.lastIndexOf("\\") + 1);
        }
        return filename;
    }

    private byte[] getBytes(Part part) throws IOException {
        try (InputStream in = part.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }

}
