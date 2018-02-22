package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

@WebServlet("/save")
public class Save extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("Big5");
	    String name = StringEscapeUtils.unescapeHtml(request.getParameter("name"));
	    saveTo("c:/workspace/ex3-3.txt", name);
	}

    private void saveTo(String file, String name) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            writer.write(name);
        }
    }
}
