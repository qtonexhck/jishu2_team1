<%--
  Created by IntelliJ IDEA.
  User: 17842
  Date: 2016/4/8
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>测试页面</title>
</head>
<body>
    <p>欢迎${user.nickname},页面正在跳转。。。</p>

</body>
<script>

    var openId = '${user.openid}';
    setTimeout(function () {
        //window.location.href = "http://192.168.22.30:8080/frontend/?openId=" + openId;
        window.location.href = "http://sanjin.ngrok.natapp.cn/frontend/#/user?open_id=" + openId;
    
    }, 500);
</script>
</html>
