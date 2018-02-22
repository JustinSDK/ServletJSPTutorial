package cc.openhome.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

public class MessageDAOJdbcImpl implements MessageDAO {
    private JdbcTemplate jdbc;
    
    public MessageDAOJdbcImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Message> messagesBy(String username) {
        return jdbc.queryForList("SELECT * FROM t_message WHERE name = ?", username).stream()
                   .map(row -> new Message(
                        row.get("NAME").toString(),
                        Long.valueOf(row.get("TIME").toString()),
                        row.get("BLABLA").toString()
                   ))
                   .collect(Collectors.toList());
    }

    @Override
    public void createMessage(Message message) {
        jdbc.update("INSERT INTO t_message(name, time, blabla) VALUES(?, ?, ?)", 
            message.getUsername(),
            message.getMillis(),
            message.getBlabla()
        );
    }

    @Override
    public void deleteMessageBy(String username, String millis) {
        jdbc.update("DELETE FROM t_message WHERE name = ? AND time = ?", 
            username,
            Long.parseLong(millis)
        );
    }

    @Override
    public List<Message> newestMessages(int n) {
        return jdbc.queryForList("SELECT * FROM t_message ORDER BY time DESC LIMIT ?", n).stream()
                   .map(row -> new Message(
                        row.get("NAME").toString(),
                        Long.valueOf(row.get("TIME").toString()),
                        row.get("BLABLA").toString()
                   ))
                   .collect(Collectors.toList());
    }

}
