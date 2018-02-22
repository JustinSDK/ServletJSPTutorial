package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns={"/asyncNumber2"},
    asyncSupported = true
)
public class AsyncNumber2 extends HttpServlet {
    private Queue<AsyncContext>  asyncs;

    @Override
    public void init() throws ServletException {
        asyncs = (Queue<AsyncContext>) getServletContext().getAttribute("asyncs2");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {
        // 必須是 text/event-stream、UTF-8
        response.setContentType("text/event-stream"); 
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        AsyncContext ctx = request.startAsync();
        ctx.setTimeout(30 * 1000);

        ctx.addListener(new AsyncListener() {
          @Override
          public void onComplete(AsyncEvent event) throws IOException {
              asyncs.remove(ctx);
          }

          @Override 
          public void onTimeout(AsyncEvent event) throws IOException {
              asyncs.remove(ctx);
          }

          @Override 
          public void onError(AsyncEvent event) throws IOException {
              asyncs.remove(ctx);
          }

          @Override 
          public void onStartAsync(AsyncEvent event) throws IOException {}
        });


        asyncs.add(ctx);
    } 
}