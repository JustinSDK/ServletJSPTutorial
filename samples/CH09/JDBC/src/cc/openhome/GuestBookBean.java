package cc.openhome;

import java.sql.*;
import java.util.*;
import java.io.*;

public class GuestBookBean implements Serializable {
    private String jdbcUri = "jdbc:h2:tcp://localhost/c:/workspace/JDBC/demo";
    private String username = "caterpillar";
    private String password = "12345678";
    public GuestBookBean() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setMessage(Message message) {
        try(Connection conn = DriverManager.getConnection(
                jdbcUri, username, password);
            Statement statement = conn.createStatement()) {

            statement.executeUpdate(
                    "INSERT INTO t_message(name, email, msg) VALUES ('"
                      + message.getName() + "', '"
                      + message.getEmail() +"', '"
                      + message.getMsg() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public List<Message> getMessages() {
        try(Connection conn = DriverManager.getConnection(
                                 jdbcUri, username, password);
            Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM t_message");
            List<Message> messages = new ArrayList<>();
            while (result.next()) {
                Message message = new Message();
                message.setId(result.getLong(1));
                message.setName(result.getString(2));
                message.setEmail(result.getString(3));
                message.setMsg(result.getString(4));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
}
