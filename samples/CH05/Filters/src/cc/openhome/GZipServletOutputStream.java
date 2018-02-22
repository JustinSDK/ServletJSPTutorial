package cc.openhome;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GZipServletOutputStream extends ServletOutputStream {
    private ServletOutputStream servletOutputStream;
    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(
            ServletOutputStream servletOutputStream) throws IOException {
        this.servletOutputStream = servletOutputStream;
        this.gzipOutputStream = new GZIPOutputStream(servletOutputStream);
    }

    public void write(int b) throws IOException {
        this.gzipOutputStream.write(b);
    }

    public GZIPOutputStream getGzipOutputStream() {
        return this.gzipOutputStream;
    }

    @Override
    public boolean isReady() {
        return this.servletOutputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.servletOutputStream.setWriteListener(writeListener);
    }

    @Override
    public void close() throws IOException {
        this.gzipOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        this.gzipOutputStream.flush();
    }

    public void finish() throws IOException {
        this.gzipOutputStream.finish();
    }
}