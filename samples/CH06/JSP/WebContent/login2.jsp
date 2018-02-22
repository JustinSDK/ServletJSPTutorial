<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="cc.openhome.User" scope="request"/>
<jsp:setProperty name="user" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登入頁面</title>
    </head>
    <body>
<%
    if(user.isValid()) {
%>
    <h1><jsp:getProperty name="user" property="name"/> 登入成功</h1>
<%
    }
    else {
%>
    <h1>登入失敗</h1>
<%
    }
%>
    </body>
</html>  