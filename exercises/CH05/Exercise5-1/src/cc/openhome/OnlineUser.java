package cc.openhome;

import java.util.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

@WebListener
public class OnlineUser implements HttpSessionListener {
    public final static Map<String, HttpSession> sessions = new HashMap<>();

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        sessions.put(session.getId(), session);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        sessions.remove(se.getSession().getId());
    }

}
