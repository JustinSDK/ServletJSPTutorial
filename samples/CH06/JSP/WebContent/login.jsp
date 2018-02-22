<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String name = "caterpillar";
    String password = "123456";

    boolean checkUser(String name, String password) {
        return this.name.equals(name) &&
                 this.password.equals(password);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登入頁面</title>
    </head>
    <body>
<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    if(checkUser(name, password)) {
%>
    <h1><%= name %> 登入成功</h1>
<%
    } else {
%>
    <h1>登入失敗</h1>
<%
    }
%>
    </body>
</html>  