<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="fileService" 
             class="cc.openhome.FileService"
	         scope="application" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>檔案管理</title>
    </head>
    <body>
        <a href="logout">登出</a>
        <form method="post" enctype="multipart/form-data"
                            action="upload"><br>
            選取檔案：<input type="file" name="file"><br><br>
           <input type="submit" value="上傳">
        </form>
        <hr>
        <table style="text-align: left;" border="1"
		       cellpadding="2" cellspacing="2">
		    <tbody>
		        <tr>
                    <td>檔案名稱</td>
                    <td>上傳時間</td>
                    <td>操作</td>
                </tr>
		    <c:forEach var="file" items="${fileService.fileList}">
		        <tr>
		            <td>${file.filename}</td>
 
		            <td>${file.localDateTime}</td>
		            <td><a href="download?id=${file.id}">下載</a> ／
		                <a href="delete?id=${file.id}">刪除</a>
		            </td>
		        </tr>
		    </c:forEach>
		    </tbody>
	    </table>
    </body>
</html>
