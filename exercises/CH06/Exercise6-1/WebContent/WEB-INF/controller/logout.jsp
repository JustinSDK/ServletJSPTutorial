<%
	request.getSession().invalidate(); 
	response.sendRedirect(getInitParameter("LOGIN_PATH"));
%>