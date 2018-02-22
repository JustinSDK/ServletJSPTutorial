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
        rowset.moveToInsertRow();
        rowset.updateString(2, message.getName());
        rowset.updateString(3, message.getEmail());
        rowset.updateString(4, message.getMsg());
        rowset.insertRow();
    }

    public List<Message> getMessages() throws SQLException {
        loadTable();
        List<Message> messages = new ArrayList<>();
        rowset.beforeFirst();
        while (rowset.next()) {
            Message message = new Message();
            message.setId(rowset.getLong(1));
            message.setName(rowset.getString(2));
            message.setEmail(rowset.getString(3));
            message.setMsg(rowset.getString(4));
            messages.add(message);
        }
        return messages;
    }

    @Override
    protected void finalize() throws Throwable {
        if(rowset != null) {
            rowset.close();
        }
    }
}
