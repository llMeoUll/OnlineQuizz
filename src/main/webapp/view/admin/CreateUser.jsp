<%--
  Created by IntelliJ IDEA.
  User: luulo
  Date: 1/27/2024
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="../admin/create" method="post">
            <label for="email">Email:</label>
            <input type="text" name="email" id="email" required> <br>
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" required> <br>
            <label for="given_name">First Name:</label>
            <input type="text" name="given_name" id="given_name" required> <br>
            <label for="family_name">Last Name:</label>
            <input type="text" name="family_name" id="family_name" required> <br>
            <label for="password">Password:</label>
            <input type="text" name="password" id="password" required> <br>
            <input type="submit" value="Create">
        </form>
    </body>
</html>
