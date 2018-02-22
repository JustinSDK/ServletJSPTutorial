package cc.openhome;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbook")
public class GuestBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");

	    PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String message = request.getParameter("message");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>GuestBook</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("Name: %s<br>", name);
        out.printf("Message: %s<br>", message);
        out.println("</body>");
        out.println("</html>");
	}

}
