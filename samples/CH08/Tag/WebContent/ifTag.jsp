<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>自訂 if 標籤</title>
    </head>
    <body>
        <f:if test="${param.password == '123456'}">
            你的秘密資料在此！
        </f:if>
    </body>
</html>
