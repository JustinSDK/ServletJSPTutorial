package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CompressionWrapper extends HttpServletResponseWrapper {
    private GZipServletOutputStream gzServletOutputStream;
    private PrintWriter printWriter;

    public CompressionWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(printWriter != null) {
            throw new IllegalStateException();
        }
        if (gzServletOutputStream == null) {
            gzServletOutputStream = 
                    new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return gzServletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(gzServletOutputStream != null) {
            throw new IllegalStateException();
        }
        if (printWriter == null) {
            gzServletOutputStream = 
                    new GZipServletOutputStream(getResponse().getOutputStream());
            OutputStreamWriter osw = 
                    new OutputStreamWriter(
                        gzServletOutputStream, getResponse().getCharacterEncoding());
            printWriter = new PrintWriter(osw);
        }
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if(this.printWriter != null) {
            this.printWriter.flush();
        }
        else if(this.gzServletOutputStream != null) {
            this.gzServletOutputStream.flush();
        }
        super.flushBuffer();
    }    

    public void finish() throws IOException {
        if(this.printWriter != null) {
            this.printWriter.close();
        }
        else if(this.gzServletOutputStream != null) {
            this.gzServletOutputStream.finish();
        }
    }

    @Override
    public void setContentLength(int len) {}

    @Override
    public void setContentLengthLong(long length) {}
}