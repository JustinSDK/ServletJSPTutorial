package cc.openhome;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsers implements HttpSessionListener {
    public static int counter;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        OnlineUsers.counter++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        OnlineUsers.counter--;
    }
}
