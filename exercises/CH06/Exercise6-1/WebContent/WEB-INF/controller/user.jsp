<%@page import="cc.openhome.model.*,java.util.*" %>
<%!
	private String getUsername(HttpServletRequest request) {
	    return request.getPathInfo().substring(1);
	}
%>
<%
	String username = getUsername(request);
	UserService userService = (UserService) getServletContext().getAttribute("userService");
	
	List<Message> messages = userService.messages(username);
	
	request.setAttribute("messages", messages);
	request.setAttribute("username", username);
	
	request.getRequestDispatcher(getInitParameter("USER_PATH"))
	       .forward(request, response);
%>