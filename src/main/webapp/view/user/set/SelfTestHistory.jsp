<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/13/2024
  Time: 7:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Self test history</title>
</head>
<body>
    <div class="container">
        <table>
           <thead>
                <tr>
                    <th>Index</th>
                    <th>Test Date</th>
                    <th>Test Result</th>
                </tr>
           </thead>
            <tbody>
                <c:forEach items="${requestScope.selfTests}" var="selfTest" varStatus="selfTestLoop">
                    <tr>
                        <td>${selfTestLoop.count}</td>
                        <fmt:formatDate value="${selfTest.createdAt}" pattern="HH:mm dd/MM/yyyy" var="formattedDate" />
                        <td>${formattedDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
