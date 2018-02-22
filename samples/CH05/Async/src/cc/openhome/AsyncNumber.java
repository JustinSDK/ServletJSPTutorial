package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns={"/asyncNumber"},
    asyncSupported = true
)
public class AsyncNumber extends HttpServlet {
    private List<AsyncContext>  asyncs;

    @Override
    public void init() throws ServletException {
        asyncs = (List<AsyncContext>) getServletContext().getAttribute("asyncs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, IOException {
        synchronized(asyncs) {
            asyncs.add(request.startAsync());
        }
    } 
}