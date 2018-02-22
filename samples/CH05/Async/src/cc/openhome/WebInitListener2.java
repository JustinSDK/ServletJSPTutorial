package cc.openhome;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener()
public class WebInitListener2 implements ServletContextListener {
    // 所有非同步請求的  AsyncContext 將儲存至這個  Queue
    private Queue<AsyncContext> asyncs = new ConcurrentLinkedQueue<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("asyncs2", asyncs);

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
        }).start();
    }

    private void response(double num) {
        // 逐一完成非同步請求
        asyncs.forEach(ctx -> {
            try {
                PrintWriter out = ctx.getResponse().getWriter();
                out.printf("data: %s\n\n", num);
                out.flush();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}