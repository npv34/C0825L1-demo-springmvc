<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 2/26/26
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List user page</h1>
<a href="/users/create">Create</a>
<table>
    <tr>
        <td>STT</td>
        <td>Email</td>
        <td>Username</td>
        <td></td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td>
                <a href="/users/<c:out value="${user.id}"/>/delete ">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
