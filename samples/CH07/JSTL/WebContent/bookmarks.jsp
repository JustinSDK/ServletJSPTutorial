<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>線上書籤</title>
    </head>
    <body>
       <c:import var="xml" url="bookmarks.xml" charEncoding="UTF-8" />
       <x:parse var="bookmarks" doc="${xml}" />
       <h2>線上書籤</h2>
       <table border="1">
           <tr bgcolor="#00ff00">
               <th align="left">名稱</th>
               <th align="left">網址</th>
               <th align="left">分類</th>
           </tr>
        <x:forEach var="bookmark" select="$bookmarks//bookmark">
            <tr>
                <td><x:out select="$bookmark/title"/></td>
                <td><x:out select="$bookmark/url"/></td>
                <td><x:out select="$bookmark/category"/></td>
            </tr>
        </x:forEach>
       </table>
    </body>
</html>
