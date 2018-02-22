<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>表單填寫錯誤</title>
</head>
<body>
    <h1>表單填寫錯誤</h1>
    <ul style='color: rgb(255, 0, 0);'>
        <%
            List<String> errors = (List<String>) request.getAttribute("errors");
            for(String error : errors) {
        %>
            <li><%= error %></li>
        <%
            }
        %>
      </ul>
    <a href='register.html'>返回註冊表單</a>
</body>
</html>