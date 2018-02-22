package cc.openhome.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

public class AccountDAOJdbcImpl implements AccountDAO {
    private JdbcTemplate jdbc;
    
    public AccountDAOJdbcImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void createAccount(Account acct) {
        jdbc.update("INSERT INTO t_account(name, email, password, salt) VALUES(?, ?, ?, ?)", 
            acct.getName(),
            acct.getEmail(),
            acct.getPassword(),
            acct.getSalt()
        );
    }

    @Override
    public Optional<Account> accountBy(String name) {
        List<Map> results = jdbc.queryForList("SELECT * FROM t_account WHERE name = ?", name);
        
        if(results.isEmpty()) {
            return Optional.empty();
        }
        
        Map row = results.get(0);
        
        return Optional.of(new Account(
                row.get("NAME").toString(),
                row.get("EMAIL").toString(),
                row.get("PASSWORD").toString(),
                row.get("SALT").toString()
            ));
    }

}
