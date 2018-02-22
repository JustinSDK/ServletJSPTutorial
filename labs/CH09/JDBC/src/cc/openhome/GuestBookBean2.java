package cc.openhome;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;

public class GuestBookBean2 implements Serializable {
    private JdbcRowSet rowset;
    public GuestBookBean2() throws SQLException {
        rowset = new JdbcRowSetImpl();
        rowset.setDataSourceName("java:/comp/env/jdbc/demo");
    }
    
    private void loadTable() throws SQLException {
        rowset.setCommand("SELECT * FROM t_message");
        rowset.execute();
    }

    public void setMessage(Message message) throws SQLException {
        loadTable();


    }

    public List<Message> getMessages() throws SQLException {
        loadTable();


    }

    @Override
    protected void finalize() throws Throwable {
        if(rowset != null) {
            rowset.close();
        }
    }
}
