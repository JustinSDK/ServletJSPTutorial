<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<html>
    <head>
        <meta charset="UTF-8"/>
    </head>
    <body>
        <c:import var="xml" url="bookmarks.xml" charEncoding="UTF-8"/>
        <c:import var="xslt" url="${param.xslt}" charEncoding="UTF-8"/>
        <x:transform doc="${xml}" xslt="${xslt}">
            <x:param name="headline" value="線上書籤"/>
        </x:transform>
    </body>
</html>
