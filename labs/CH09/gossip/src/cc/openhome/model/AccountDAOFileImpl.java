package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AccountDAOFileImpl implements AccountDAO {
    private final String USERS;

    public AccountDAOFileImpl(String USERS) {
        this.USERS = USERS;
    }

    @Override
    public void createAccount(Account acct) {
        Path userhome = Paths.get(USERS, acct.getName());
        try {
            Files.createDirectories(userhome);
            Path profile = userhome.resolve("profile");
            try (BufferedWriter writer = Files.newBufferedWriter(profile)) {
                writer.write(String.format("%s\t%s\t%s", acct.getEmail(), acct.getPassword(), acct.getSalt()));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Optional<Account> accountBy(String name) {
        Path userhome = Paths.get(USERS, name);
        if(Files.notExists(userhome)) {
            return Optional.empty();
        }
        return readProfile(userhome, name);
    }

    private Optional<Account> readProfile(Path userhome, String name)  {
        Path profile = userhome.resolve("profile");
        try (BufferedReader reader = Files.newBufferedReader(profile)) {
            String[] data = reader.readLine().split("\t");
            String email = data[0];
            String encrypt = data[1];
            String salt = data[2];
            return Optional.of(new Account(name, email, encrypt, salt));
        } catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
