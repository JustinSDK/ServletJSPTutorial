<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>啟用帳號</title>
</head>
<body>
	<f:choose>
	    <f:when test="${requestScope.acct.present}">
	        <h1>帳號啟用成功</h1>
	    </f:when>
	    <f:otherwise>
	        <h1>帳號啟用失敗</h1>
	    </f:otherwise>
	</f:choose>
	<a href='/gossip'>回首頁</a>
</body>
</html>