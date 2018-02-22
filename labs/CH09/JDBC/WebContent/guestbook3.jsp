<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<c:set target="${pageContext.request}"
         property="characterEncoding" value="UTF-8"/>
<c:if test="${param.msg != null}">


</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>訪客留言版</title>
    </head>
    <body>
        <table style="text-align: left; width: 100%;" border="0"
               cellpadding="2" cellspacing="2">
            <tbody>


                <c:forEach var="message" items="${messages.rows}">
                    <tr>
                        <td>${message.name}</td>
                        <td>${message.email}</td>
                        <td>${message.msg}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
