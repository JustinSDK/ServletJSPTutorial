package cc.openhome;

import java.io.*;
import java.util.concurrent.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns={"/async"},
    asyncSupported = true
)
public class AsyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF8");
        AsyncContext ctx = request.startAsync();
        doAsync(ctx).thenApplyAsync(String::toUpperCase)
                    .thenAcceptAsync(resource -> {
                        try {
                            ctx.getResponse().getWriter().println(resource);
                            ctx.complete();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
    } 

    private CompletableFuture<String> doAsync(AsyncContext ctx) {
        return CompletableFuture.supplyAsync(() -> {
             try {
                String resource = ctx.getRequest().getParameter("resource");
                Thread.sleep(10000); // 模擬長時間的處理
                return String.format("%s back finally...XD", resource);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }  
        });
    }
}