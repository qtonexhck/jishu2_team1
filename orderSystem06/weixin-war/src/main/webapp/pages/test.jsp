<%--
  Created by IntelliJ IDEA.
  User: defore
  Date: 16/4/2
  Time: 下午11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        img{
            width: 400px;
        }
    </style>
</head>
<body>
    <h2>欢迎访问</h2>
    <p>我的openid是:${user.openid}</p>
    <p>${user.nickname}</p>
    <p>我的头像</p>
    <img src="${user.headimgurl}" alt="">
</body>
<script>
//    setTimeout(function () {
//        window.location.href = "http://defore.ngrok.cc/front";
//    }, 3000);
</script>
</html>
