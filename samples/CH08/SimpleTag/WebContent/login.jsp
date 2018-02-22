<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake"%>
<jsp:useBean id="user" class="cc.openhome.User"  />
<jsp:setProperty name="user" property="*" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登入頁面</title>
    </head>
    <body>
        <f:choose>
            <f:when test="${user.valid}">
                <h1>${user.name}登入成功</h1>
            </f:when>
            <f:otherwise>
                 <h1>登入失敗</h1>
            </f:otherwise>
        </f:choose>
    </body>
</html>
