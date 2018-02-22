package cc.openhome;

import cc.openhome.model.UserService;

public class Main {
    public static void main(String[] ags) {
        UserService userService = Service.getUserService();
        
        userService.messages("caterpillar")
                   .forEach(message -> {
                       System.out.printf("%s\t%s%n", message.getLocalDateTime(), message.getBlabla());
                   });
    }
}
