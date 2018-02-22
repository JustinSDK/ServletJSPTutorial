<%@page import="java.util.*,java.time.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Gossip 微網誌</title>
<link rel='stylesheet' href='css/member.css' type='text/css'>
</head>
<body>

    <div class='leftPanel'>
        <img src='images/caterpillar.jpg' alt='Gossip 微網誌' /><br>
        <br> <a href='logout'>登出 ${sessionScope.login}</a>
    </div>
    <form method='post' action='new_message'>
        分享新鮮事...<br>
        <%
            String preBlabla = request.getParameter("blabla");
            if (preBlabla != null) {
        %>
        訊息要 140 字以內<br>
        <%
            }
        %>

        <textarea cols='60' rows='4' name='blabla'>${param.blabla}</textarea>
        <br>
        <button type='submit'>送出</button>
    </form>
    <table border='0' cellpadding='2' cellspacing='2'>
        <thead>
            <tr>
                <th><hr></th>
            </tr>
        </thead>
        <tbody>

            <%
                Map<Long, String> messages = (Map<Long, String>) request.getAttribute("messages");
            
                for(Map.Entry<Long, String> message : messages.entrySet()) {
                    Long millis = message.getKey();
                    String blabla = message.getValue();

                    LocalDateTime dateTime = Instant.ofEpochMilli(millis)
                                                    .atZone(ZoneId.of("Asia/Taipei"))
                                                    .toLocalDateTime();
            %>
            <tr>
                <td style='vertical-align: top;'>${sessionScope.login}<br>
                    <%=blabla%><br> <%=dateTime%>
                    <form method='post' action='del_message'>
                        <input type='hidden' name='millis' value='<%=millis%>'>
                        <button type='submit'>刪除</button>
                    </form>
                    <hr>
                </td>
            </tr>
            <%
                }
            %>

        </tbody>
    </table>
    <hr>
</body>
</html>