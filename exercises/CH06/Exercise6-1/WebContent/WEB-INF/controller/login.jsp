<%@page import="cc.openhome.model.*,java.util.*" %>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	UserService userService = (UserService) getServletContext().getAttribute("userService");
	
	if(userService.login(username, password)) {
	    if(request.getSession(false) != null) {
	        request.changeSessionId();
	    }
	    request.getSession().setAttribute("login", username);
	    response.sendRedirect(getInitParameter("SUCCESS_PATH"));
	} else {
	    request.setAttribute("errors", Arrays.asList("登入失敗"));
	    request.getRequestDispatcher(getInitParameter("ERROR_PATH"))
	           .forward(request, response);
	}
%>