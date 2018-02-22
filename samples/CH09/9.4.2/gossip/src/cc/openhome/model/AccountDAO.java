package cc.openhome.model;

import java.util.Optional;

public interface AccountDAO {
    void createAccount(Account acct);
    Optional<Account> accountBy(String name);
}
