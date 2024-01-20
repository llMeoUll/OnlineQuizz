<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: luulo
  Date: 1/14/2024
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>DashBoard</title>
    </head>
    <body>
        <input type="search" placeholder="Input name of user">
        <table>
            <thead>
            <tr>
                <th>UserID</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Name</th>
                <th>Role</th>
                <th>Avatar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.uid}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.displayName}</td>
                    <td>${user.role.rid}</td>
                    <td>
                        <!-- Displaying the avatar as an image using a data URI -->
                        <img src="data:image/jpeg;base64,${user.avatar}" alt="Avatar">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
