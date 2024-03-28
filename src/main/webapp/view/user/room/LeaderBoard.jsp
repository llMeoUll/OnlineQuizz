<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="ftm" %>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" href="../../.././css/manageRoom/ViewTestDetail.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet"/>
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%--Style body--%>
<jsp:include page="../../../components/header.jsp"/>

<div class="container" style="margin-top: 96px">
    <h2 class="mb-4 text-center">Leaderboard</h2>
    <div class="text-center">
        <a class="mb-3 text-decoration-none fs-3"
           href="${pageContext.request.contextPath}/user/room/test/get?testId=${requestScope.test.testId}">${requestScope.test.testName}</a>

    </div>
    <p class="mb-4 text-center">${requestScope.test.testDescription}</p>
    <table class="table table-bordered table-striped text-light" id="leader-table">
        <thead>
        <tr>
            <th>Rank</th>
            <th>Submitter Name</th>
            <th>Score</th>
            <th>Grade / 10.00</th>
            <th>Attempt</th>
            <th>Submission Date</th>
            <th>Test Id</th>
            <th>User Id</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="submission" items="${requestScope.leaderBoardViewModels}" varStatus="leaderboardLoop">
            <tr>
                <td>${leaderboardLoop.count}</td>
                <td>${submission.user.username != null ? submission.user.username : submission.user.email }</td>
                <td>${submission.score}</td>
                <td><c:out
                        value="${String.format('%.2f', ((submission.score / submission.totalScore) * 100) / 10.00)}"/></td>
                <td>${submission.orderAttempt}</td>
                <ftm:formatDate value="${submission.createdAt}" pattern="HH:mm dd/MM/yyyy" var="submitRecord"/>
                <td>${submitRecord}</td>
                <td>${submission.test.getTestId()}</td>
                <td>${submission.user.id}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/LeaderBoard.js"></script>
</body>
</html>
