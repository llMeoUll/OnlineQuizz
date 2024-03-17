<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="../../.././css/manageRoom/ViewTestDetail.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<%--Style body--%>

<div class="container mt-5">
    <h2 class="mb-4 text-center">Leaderboard</h2>
    <h2 class="mb-3 text-center">${requestScope.leaderBoardViewModels[0].test.testName}</h2>
    <p class="mb-4 text-center">${requestScope.leaderBoardViewModels[0].test.testDescription}</p>
    <table class="table table-bordered table-striped text-light">
        <thead>
        <tr>
            <th>Attempt</th>
            <th>Submission Date</th>
            <th>Submitter Name</th>
            <th>Score</th>
            <th>Grade / 10.00</th>
            <th>Review</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="submission" items="${requestScope.leaderBoardViewModels}">
            <tr>
                <td>${submission.orderAttempt}</td>
                <td>${submission.createdAt}</td>
                <td>${submission.user.username}</td>
                <td>${submission.score}</td>
                <td><c:out value="${String.format('%.2f', ((submission.score / submission.totalScore) * 100) / 10.00)}"/></td>
                <td><a class="text-light" href="./reviewtest?testId=${submission.test.getTestId()}&&attempt=${submission.orderAttempt}&&userId=${submission.user.id}">Review test</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
