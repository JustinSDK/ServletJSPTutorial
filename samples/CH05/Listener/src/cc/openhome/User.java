package cc.openhome;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
    private String name;
    private String data;
    
    public User(String name) {
        this.name = name;
    }

    public void valueBound(HttpSessionBindingEvent event) {
        this.data = name + " 來自資料庫的資料...";
    }
    
    public String getData() {
        return data;
    }
    public String getName() {
        return name;
    }
}
