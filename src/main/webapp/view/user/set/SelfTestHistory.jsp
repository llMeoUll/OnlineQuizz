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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<div class="container" style="margin-top: 96px">
    <h4 class="text-center">Self test history</h4>
    <div class="row justify-content-center">
        <a href=".././get?setId=${requestScope.set.getSId()}" class="text-decoration-none text-center">${requestScope.set.getSName()}</a>
    </div>
    <table id="self-test-history" class="table table-hover">
        <thead>
        <tr>
            <th>Index</th>
            <th>Test Result</th>
            <th>Test record</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.selfTestHistories}" var="selfTestHistory" varStatus="selfTestHistoryLoop">
            <tr>
                <td>${selfTestHistoryLoop.count}</td>
                <td>${selfTestHistory.result}/${selfTestHistory.numberOfQuestion}</td>
                <fmt:formatDate value="${selfTestHistory.createdAt}" pattern="HH:mm dd/MM/yyyy" var="formattedDate"/>
                <td>${formattedDate}</td>
                <td>${selfTestHistory.selfTestId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../../.././js/SelfTestHistory.js"></script>
</body>
</html>
