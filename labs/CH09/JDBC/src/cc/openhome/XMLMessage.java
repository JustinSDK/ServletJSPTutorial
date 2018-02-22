package cc.openhome;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.rowset.WebRowSet;
import com.sun.rowset.WebRowSetImpl;

@WebServlet("/xmlMessage")
public class XMLMessage extends HttpServlet {
    private WebRowSet rowset = null;
    
    @Override
    public void init() throws ServletException {
        try {
            rowset = new WebRowSetImpl();
            rowset.setDataSourceName("java:/comp/env/jdbc/demo");
            rowset.setCommand("SELECT * FROM t_message");
            rowset.execute();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                           throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        try {
            rowset.writeXml(response.getOutputStream());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
