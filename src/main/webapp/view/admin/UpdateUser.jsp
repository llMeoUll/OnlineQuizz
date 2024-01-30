<%--
  Created by IntelliJ IDEA.
  User: luulo
  Date: 1/24/2024
  Time: 2:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>UpdateUser</title>
    </head>
    <body>
        <form action="./update" method="post">
            UserID: <input type="hidden" name="uid" value="${requestScope.userForUpdate.id}"> <br>
            Email: <input type="text" name="email" value="${requestScope.userForUpdate.email}"> <br>
            Username: <input type="text" name="username" value="${requestScope.userForUpdate.username}"> <br>
            Given Name: <input type="text" name="givenName" value="${requestScope.userForUpdate.givenName}"> <br>
            Family Name: <input type="text" name="familyName" value="${requestScope.userForUpdate.familyName}"> <br>
<%--            Avatar: <input type="text" name="avatar" value="${requestScope.userForUpdate.avatar}"> <br>--%>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
