<%@tag description="顯示錯誤訊息的標籤" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${requestScope.errors != null}">
    <h1>新增會員失敗</h1>
    <ul style='color: rgb(255, 0, 0);'>
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
    </ul><br>
</c:if>