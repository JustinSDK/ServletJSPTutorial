<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="g" uri="http://openhome.cc/graphics"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>自訂 eachImage 標籤</title>
</head>
<body>
	<g:eachImage var="image" dir="/avatars">
		<img src="${image}">
		<br>
	</g:eachImage>
</body>
</html>