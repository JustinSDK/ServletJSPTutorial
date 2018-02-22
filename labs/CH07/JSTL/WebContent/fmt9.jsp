<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <fmt:setLocale value="zh_TW"/>
        <fmt:formatDate value="${now}" type="both"/><br>
        <fmt:formatNumber value="12345.678" type="currency"/><br>
        <fmt:setLocale value="en_US"/>
        <fmt:formatDate value="${now}" type="both"/><br>
        <fmt:formatNumber value="12345.678" type="currency"/><br>
        <fmt:setLocale value="ja_JP"/>
        <fmt:formatDate value="${now}" type="both"/><br>
        <fmt:formatNumber value="12345.678" type="currency"/><br>
    </body>
</html>
