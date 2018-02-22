<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="util" uri="https://openhome.cc/util"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>自訂EL函式</title>
    </head>
    <body>
        ${ util:length([100, 95, 88, 75]) }
    </body>
</html>