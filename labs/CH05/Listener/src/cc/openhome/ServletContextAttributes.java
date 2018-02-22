package cc.openhome;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextAttributes implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // 實作屬性設定
    }
}
