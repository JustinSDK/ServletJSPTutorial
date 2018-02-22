<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,javax.servlet.jsp.jstl.core.*"%>
<%@ page import="javax.servlet.jsp.jstl.fmt.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    Locale locale = new Locale("ja", "JP");
    ResourceBundle res = ResourceBundle.getBundle("hello", locale);
    Config.set(pageContext, Config.FMT_LOCALIZATION_CONTEXT,
         new LocalizationContext(res), PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
	    <fmt:message key="cc.openhome.hello"/>
    </body>
</html> 
