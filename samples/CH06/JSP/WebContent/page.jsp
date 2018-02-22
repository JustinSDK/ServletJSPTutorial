<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Page 指示元素</title>
    </head>
    <body>
        <h1>現在時間: <%= LocalDateTime.now() %> </h1>
    </body>
</html>