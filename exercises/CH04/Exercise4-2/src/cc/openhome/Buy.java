package cc.openhome;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/buy")
public class Buy extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	                  throws ServletException, IOException {
	    String book = request.getParameter("book");
	    if(book != null && book.trim().length() != 0) {
	        HttpSession session = request.getSession();
	        if(session.getAttribute("cart") != null) {
	            List<String> cart = (List<String>) session.getAttribute("cart");
	            cart.add(book);
	        }
	    }
	    response.sendRedirect("shopping.view");
	}
}
