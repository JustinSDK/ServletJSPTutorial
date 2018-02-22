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

    }

    public List<Message> getMessages() {

    }
}
