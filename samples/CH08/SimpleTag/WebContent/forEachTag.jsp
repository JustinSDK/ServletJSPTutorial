<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    request.setAttribute("errors", java.util.Arrays.asList("XD", "Orz"));
%>

<f:forEach items="${errors }" var="error"> 
    ${error}
</f:forEach>
</body>

</html>