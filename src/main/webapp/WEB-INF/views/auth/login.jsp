<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 2/24/26
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login page</h1>
<form action="${pageContext.request.contextPath}/auth/login" method="post">
    <label for="username">Username</label>
    <input type="text" id="username" name="username">
    <br/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password">
    <br/>
    <button type="submit">Login</button>
</form>
</body>
</html>
