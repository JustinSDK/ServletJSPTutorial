<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
    <c:when test="${param.locale == 'zh_TW' || param.locale == 'zh_GB'}">
        <fmt:setLocale value="${param.locale}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_US"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="cc.openhome.title"/></title>
</head>
<body>
<c:choose>
    <c:when test="${param.locale != null}">
        <a href="index.jsp">English</a>
    </c:when>
    <c:otherwise>
        English
    </c:otherwise>
</c:choose>　
<c:choose>
    <c:when test="${param.locale != 'zh_TW'}">
        <a href="index.jsp?locale=zh_TW">正體中文</a>
    </c:when>
    <c:otherwise>
                 正體中文
    </c:otherwise>
</c:choose>　
<c:choose>
    <c:when test="${param.locale != 'zh_GB'}">
        <a href="index.jsp?locale=zh_GB">简体中文</a>
    </c:when>
    <c:otherwise>
                 简体中文
    </c:otherwise>
</c:choose>
    <hr>
    <fmt:message key="cc.openhome.message"/>
</body>
</html>