package cc.openhome;

import java.io.Serializable;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class DatabaseBean implements Serializable {
    private DataSource dataSource;

    public DatabaseBean() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/demo");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }

    }

    public boolean isConnectedOK() {
        try(Connection conn = dataSource.getConnection()) {
            return !conn.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
} 
