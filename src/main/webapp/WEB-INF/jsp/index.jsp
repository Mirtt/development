<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>登录</h2>
<form action="/login" method="post">
    <label for="userAccount">账号:</label>
    <input id="userAccount" type="text" name="account"/><br/>
    <label for="password">密码：</label>
    <input id="password" type="password" name="password"><br/>
    <input type="submit" value="submit"/>
</form>
</body>
</html>
