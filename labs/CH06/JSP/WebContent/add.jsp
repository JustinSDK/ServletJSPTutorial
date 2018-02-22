<%@page contentType="text/html"
        pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加法網頁</title>
</head>
<body>
<%
    String a = request.getParameter("a");
    String b = request.getParameter("b");
    out.println("a + b = " + 
                (Integer.parseInt(a) + Integer.parseInt(b))
            );
%>
</body>
</html> 