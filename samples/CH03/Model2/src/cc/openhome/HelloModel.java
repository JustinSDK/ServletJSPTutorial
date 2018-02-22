package cc.openhome;

import java.util.*;

public class HelloModel {
    private Map<String, String> messages = new HashMap<>();

    public HelloModel() {
        messages.put("caterpillar", "Hello");
        messages.put("Justin", "Welcome");
        messages.put("momor", "Hi");
    }

    public String doHello(String user) {
        String message = messages.get(user);
        return String.format("%s, %s!", message, user);
    }
}
