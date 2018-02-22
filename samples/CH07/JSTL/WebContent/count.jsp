<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="count" value="${sessionScope.count + 1}" scope="session"/>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP Count</title>
    </head>
    <body>
        <h1>JSP Count ${sessionScope.count} </h1>
        <a href="<c:url value='count.jsp'/>">遞增</a>
    </body>
</html> 