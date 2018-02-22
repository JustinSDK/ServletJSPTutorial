<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="db" class="cc.openhome.DbBean"/>
<c:set target="${db}" property="jdbcUri"
         value="jdbc:h2:tcp://localhost/c:/workspace/JDBC/demo"/>
<c:set target="${db}" property="username" value="caterpillar"/>
<c:set target="${db}" property="password" value="12345678"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>測試資料庫連線</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${db.connectedOK}">連線成功！</c:when>
            <c:otherwise>連線失敗！</c:otherwise>
        </c:choose>
    </body>
</html>
