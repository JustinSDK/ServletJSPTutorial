package cc.openhome.model;

public class Account {
    private String name;
    private String email;
    private String password;
    private String salt;
    private int enabled;
    
    public Account(String name, String email, String password, String salt, int enabled) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public boolean isEnabled() {
        return enabled == 1;
    }
}
