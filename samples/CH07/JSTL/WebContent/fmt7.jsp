<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <fmt:formatDate value="${now}"/><br>
        <fmt:formatDate value="${now}" dateStyle="full"/><br>
        <fmt:formatDate value="${now}"
                           type="time" timeStyle="full"/><br>
        <fmt:formatDate value="${now}" pattern="dd.MM.yy"/><br>
        <fmt:timeZone value="GMT+1:00">
            <fmt:formatDate value="${now}" type="both"
                               dateStyle="full" timeStyle="full"/><br>
        </fmt:timeZone>
    </body>
</html>
