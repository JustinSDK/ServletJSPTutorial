<%@tag description="HTML 懶人標籤" pageEncoding="UTF-8"%>
<%@attribute name="title"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/member.css" type="text/css">    
        <title>${title}</title>
    </head>
    <body>
        <jsp:doBody/>
    </body>
</html>