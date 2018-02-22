package cc.openhome;

import org.h2.jdbcx.JdbcDataSource;

import cc.openhome.model.AccountDAO;
import cc.openhome.model.AccountDAOJdbcImpl;
import cc.openhome.model.MessageDAO;
import cc.openhome.model.MessageDAOJdbcImpl;
import cc.openhome.model.UserService;

public class Service {
    public static UserService getUserService() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/c:/workspace/SpringDI/gossip");
        dataSource.setUser("caterpillar");
        dataSource.setPassword("12345678");
        
        AccountDAO acctDAO = new AccountDAOJdbcImpl(dataSource);
        MessageDAO messageDAO = new MessageDAOJdbcImpl(dataSource);
        
        UserService userService = new UserService(acctDAO, messageDAO);
        return userService;
    }
}
