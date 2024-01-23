<%--
  Created by IntelliJ IDEA.
  User: luulo
  Date: 1/15/2024
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>User Management</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>User ID</td>
                    <td>User Email</td>
                    <td>Username</td>
                    <td>Given Name</td>
                    <td>Family Name</td>
                    <td>Avatar</td>
                    <td>Created At</td>
                    <td>Updated At</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.username}</td>
                    <td>${user.givenName}</td>
                    <td>${user.familyName}</td>
                    <td>${user.picture}</td>
                    <td>${user.createdAt}</td>
                    <td>${user.updatedAt}</td>
                    <td>
                        <button>Delete</button>
                    </td>
                    <td>
                        <button>Update</button>
                    </td>
                    <td>
                        <button>View Profile</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>
