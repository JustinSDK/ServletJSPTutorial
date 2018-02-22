package cc.openhome.model;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountDAOJdbcImpl implements AccountDAO {
    private JdbcTemplate jdbc;
    
    @Autowired
    public AccountDAOJdbcImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void createAccount(Account acct) {
        jdbc.update("INSERT INTO t_account(name, email, password, salt, enabled) VALUES(?, ?, ?, ?, 0)", 
                  acct.getName(), acct.getEmail(), acct.getPassword(), acct.getSalt());
        jdbc.update("INSERT INTO t_account_role(name, role) VALUES(?, 'member')", 
                acct.getName());
    }

    @Override
    public Optional<Account> accountByUsername(String name) {
        return jdbc.queryForList("SELECT * FROM t_account WHERE name = ?", name)
                    .stream()
                    .findFirst()
                    .map(row -> {
                       return new Account(
                               row.get("NAME").toString(),
                               row.get("EMAIL").toString(),
                               row.get("PASSWORD").toString(),
                               row.get("SALT").toString(),
                               Integer.valueOf(row.get("ENABLED").toString())
                           );
                    });
    }

    @Override
    public Optional<Account> accountByEmail(String email) {
        return jdbc.queryForList("SELECT * FROM t_account WHERE email = ?", email)
                .stream()
                .findFirst()
                .map(row -> {
                   return new Account(
                           row.get("NAME").toString(),
                           row.get("EMAIL").toString(),
                           row.get("PASSWORD").toString(),
                           row.get("SALT").toString(),
                           Integer.valueOf(row.get("ENABLED").toString())
                       );
                });
    }
    
    public void activateAccount(Account acct) {
        jdbc.update("UPDATE t_account SET enabled = ? WHERE name = ?", 1, acct.getName());
    }

    @Override
    public void updatePasswordSalt(String name, String password, String salt) {
        jdbc.update("UPDATE t_account SET password = ?, salt = ? WHERE name = ?", password, salt, name);
    }
}
