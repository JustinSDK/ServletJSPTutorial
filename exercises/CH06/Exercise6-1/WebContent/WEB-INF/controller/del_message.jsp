<%@page import="cc.openhome.model.*" %>
<%!
	private String getUsername(HttpServletRequest request) {
	    return (String) request.getSession().getAttribute("login");
	}
%>
<%
	String millis = request.getParameter("millis");
	
	if(millis != null) {
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    userService.deleteMessage(getUsername(request), millis);
	}
	
	response.sendRedirect(getInitParameter("MEMBER_PATH"));
%>