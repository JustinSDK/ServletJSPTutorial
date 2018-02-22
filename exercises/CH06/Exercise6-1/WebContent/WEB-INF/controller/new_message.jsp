<%@page import="cc.openhome.model.*" %>
<%!
	private String getUsername(HttpServletRequest request) {
	    return (String) request.getSession().getAttribute("login");
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	String blabla = request.getParameter("blabla");
	
	if(blabla == null || blabla.length() == 0) {
	    response.sendRedirect(getInitParameter("MEMBER_PATH"));
	    return;
	}        
	
	if(blabla.length() <= 140) {
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    userService.addMessage(getUsername(request), blabla);
	    response.sendRedirect(getInitParameter("MEMBER_PATH"));
	}
	else {
	    request.getRequestDispatcher(getInitParameter("MEMBER_PATH")).forward(request, response);
	}
%>