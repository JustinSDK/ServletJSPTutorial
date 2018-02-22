package cc.openhome;

import java.sql.*;
import java.io.*;

public class DbBean implements Serializable {
    private String jdbcUri;
    private String username;
    private String password;

    public DbBean() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isConnectedOK() {
        try(Connection conn = DriverManager.getConnection(
                jdbcUri, username, password)) {
            return !conn.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setJdbcUri(String jdbcUri) {
        this.jdbcUri = jdbcUri;
    }

    public void setUsername(String username) {
        this.username = username;
    }
} 
