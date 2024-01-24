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
        <form action="/admin/update">
            UserID: ${requestScope.userForUpdate.id}
            Email: <input type="text" name="email" value="${requestScope.userForUpdate.email}">
        </form>
    </body>
</html>
