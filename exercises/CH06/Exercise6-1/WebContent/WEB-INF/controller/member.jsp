<%@page import="cc.openhome.model.*,java.util.*" %>
<%!
	private String getUsername(HttpServletRequest request) {
	    return (String) request.getSession().getAttribute("login");
	}
%>
<%
	UserService userService = (UserService) getServletContext().getAttribute("userService");
	List<Message> messages = userService.messages(getUsername(request));
	
	request.setAttribute("messages", messages);
	request.getRequestDispatcher(getInitParameter("MEMBER_PATH")).forward(request, response);
%>