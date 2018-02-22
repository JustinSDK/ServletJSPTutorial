package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebInitParam;

@WebServlet(
    name="Login", 
    urlPatterns = {"/login.do"},
    initParams = {
        @WebInitParam(name = "SUCCESS", value = "success.view"),
        @WebInitParam(name = "ERROR", value = "error.view")
    }
)
public class Login extends HttpServlet {
    private String SUCCESS_PATH;
    private String ERROR_PATH;

    @Override
    public void init() throws ServletException {
        SUCCESS_PATH = getInitParameter("SUCCESS");
        ERROR_PATH = getInitParameter("ERROR");
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        String path = login(name, passwd) ? SUCCESS_PATH : ERROR_PATH;
        response.sendRedirect(path);
    }

    private boolean login(String name, String passwd) {
        return "caterpillar".equals(name) && "123456".equals(passwd);
    }
}
