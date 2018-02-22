<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>重設密碼</title>
</head>
<body>
    <h1>重設密碼</h1>
    
        <f:if test="${requestScope.errors != null}">
            <ul style='color: rgb(255, 0, 0);'>
            <f:forEach var="error" items="${requestScope.errors}">
                <li>${error}</li>
            </f:forEach>
            </ul>
        </f:if>
            
    <form method='post' action='reset_password'>
        <input type='hidden' name='name' value='${requestScope.acct.name}'>
        <input type='hidden' name='email' value='${requestScope.acct.email}'>
        <input type='hidden' name='token' value='${sessionScope.token}'>
        <table>
            <tr>
                <td>密碼（8 到 16 字元）：</td>
                <td><input type='password' name='password' size='25' maxlength='16'></td>
            </tr>
            <tr>
                <td>確認密碼：</td>
                <td><input type='password' name='password2' size='25' maxlength='16'></td>
            </tr>
            <tr>
                <td colspan='2' align='center'><input type='submit' value='確定'></td>
            </tr>
        </table>
    </form>
</body>
</html>