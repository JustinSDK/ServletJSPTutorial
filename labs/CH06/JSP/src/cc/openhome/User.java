package cc.openhome;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return "caterpillar".equals(name) && "123456".equals(password);
    }
} 