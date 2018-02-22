package cc.openhome;

import java.sql.*;
import java.io.*;

public class DbBean implements Serializable {
    private String jdbcUri;
    private String username;
    private String password;



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
