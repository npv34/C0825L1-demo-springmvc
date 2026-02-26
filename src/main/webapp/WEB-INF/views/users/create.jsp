<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 2/26/26
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
h1>Create user page</h1>
<form action="/users/store" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email">
    <br/>
    <label for="username">Username</label>
    <input type="text" id="username" name="username">
    <br/>
    <label for="username">Pass</label>
    <input type="text" id="pass" name="password">
    <button type="submit">Create</button>
</form>
</body>
</html>
