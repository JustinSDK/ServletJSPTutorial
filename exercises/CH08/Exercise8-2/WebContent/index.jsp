<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://openhome.cc/jstl/fake"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>自訂 set、remove 標籤</title>
</head>
<body>
<f:set var="xx" value="test" scope="request"/>
${requestScope.xx}
<f:remove var="xx" scope="request"/>
${requestScope.xx}
</body>
</html>