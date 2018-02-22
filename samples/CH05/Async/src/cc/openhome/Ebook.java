package cc.openhome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns = { "/ebook" }, 
    initParams = {
    @WebInitParam(name = "PDF_FILE", value = "/WEB-INF/jdbc.pdf") }, 
    asyncSupported = true
)
public class Ebook extends HttpServlet {
    private String PDF_FILE;

    @Override
    public void init() throws ServletException {
        super.init();
        PDF_FILE = getInitParameter("PDF_FILE");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String coupon = request.getParameter("coupon");

        if ("123456".equals(coupon)) {
            AsyncContext ctx = request.startAsync();

            ServletOutputStream out = response.getOutputStream();
            out.setWriteListener(new WriteListener() {
                InputStream in = getServletContext().getResourceAsStream(PDF_FILE);

                @Override
                public void onError(Throwable t) {
                    try {
                        in.close();
                    }
                    catch(IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                    throw new RuntimeException(t);
                }

                @Override
                public void onWritePossible() throws IOException {
                    byte[] buffer = new byte[1024];
                    int length = 0;
                    while (out.isReady() && (length = in.read(buffer)) != -1) {
                        out.write(buffer, 0, length);
                    }
                    if(length == -1) {
                        in.close();
                        ctx.complete();
                    }
                }
            });
        }
    }
}