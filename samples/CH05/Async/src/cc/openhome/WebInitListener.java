package cc.openhome;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener()
public class WebInitListener implements ServletContextListener {
   // 所有非同步請求的 AsyncContext 將儲存至這個 List
    private List<AsyncContext> asyncs = new ArrayList<>();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("asyncs", asyncs);

        new Thread(() -> {
                while (true) {
                    try {
                        // 模擬不定時
                        Thread.sleep((int) (Math.random() * 5000));
                        // 隨機產生數字
                        response(Math.random() * 10);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        ).start();
    }

    private void response(double num) {
        // 逐一完成非同步請求
        synchronized(asyncs) {
            asyncs.forEach(ctx -> {
                try {
                    ctx.getResponse().getWriter().println(num);
                    ctx.complete();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            asyncs.clear();
        }
    }
}