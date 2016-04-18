<%--
  Created by IntelliJ IDEA.
  User: defore
  Date: 16/4/7
  Time: 上午8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的点餐</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>姓名</th>
                <th>openId</th>
                <th>餐牌号</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.nickname}</td>
                <td>${user.openid}</td>
                <td>A餐</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
