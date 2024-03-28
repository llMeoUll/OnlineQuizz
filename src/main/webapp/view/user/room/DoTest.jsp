<%--
  Created by IntelliJ IDEA.
  User: Khanh Long Tran
  Date: 2/22/2024
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>


<html>
<head>
    <title>View list question in test</title>
    <link rel="stylesheet" href="../../.././css/manageRoom/ViewTestDetail.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"/>



<!-- Content -->
<div class="container" style="margin-top: 96px">
    <div class="card  mb-3">
        <!-- Card Body -->
        <div class="card-body d-flex justify-content-between align-items-center">
            <!-- Information Block for Current Room -->
            <div>
                <p class=" fs-5 font-weight-bold">${requestScope.currentTest.testName}</p>
                <p class=" fs-5 font-weight-bold">${requestScope.currentTest.testDescription}</p>
                <p class=" fs-5 font-weight-bold">Start time: ${requestScope.currentTest.startTime}
                    - End time: ${requestScope.currentTest.endTime} </p>
                <h2 id="timer" class=" fs-3 font-weight-bold text-center mb-4"></h2>
            </div>
        </div>
    </div>
</div>

<%--Display list question --%>
<div class="container mt-3">
    <table class="table table-striped">
        <tbody>
        <form id="quizForm" action="./dotest" method="post">
            <input type="hidden" name="testId" value="${requestScope.currentTest.testId}">

            <c:forEach items="${requestScope.listQuestions}" var="question">
                <tr>
                    <!-- Column for Questions -->
                    <td>
                        <div class="container mt-1  p-3 rounded room-item">
                            <!-- Card Body -->
                            <div class="card-body">
                                <!-- Information Block -->
                                <div class="row">
                                    <p class="font-weight-bold mb-0  ml-4">${question.question}</p>
                                </div>
                            </div>
                        </div>
                    </td>
                        <%--Column for Answers For ABCD --%>
                    <td>
                        <c:choose>
                            <c:when test="${question.type.getTypeId() == 1}">
                                <%-- For type_id = 1 (A to K) --%>
                                <c:forEach items="${question.questionOptions}" var="option" varStatus="loop">
                                    <div class="row ">
                                        <div class="answer-option">
                                            <c:choose>
                                                <c:when test="${loop.index == 0}">
                                                    A.
                                                </c:when>
                                                <c:when test="${loop.index == 1}">
                                                    B.
                                                </c:when>
                                                <c:when test="${loop.index == 2}">
                                                    C.
                                                </c:when>
                                                <c:when test="${loop.index == 3}">
                                                    D.
                                                </c:when>
                                                <c:when test="${loop.index == 4}">
                                                    E.
                                                </c:when>
                                                <c:when test="${loop.index == 5}">
                                                    F.
                                                </c:when>
                                                <c:when test="${loop.index == 6}">
                                                    G.
                                                </c:when>
                                                <c:when test="${loop.index == 7}">
                                                    H.
                                                </c:when>
                                                <c:when test="${loop.index == 8}">
                                                    I.
                                                </c:when>
                                                <c:when test="${loop.index == 9}">
                                                    J.
                                                </c:when>
                                                <c:when test="${loop.index == 10}">
                                                    K.
                                                </c:when>
                                            </c:choose>
                                            <input type="radio" id="option${loop.index}_${question.getQId()}"
                                                   name="answer_${question.getQId()}"
                                                   value="${option.optContent}">
                                            <label for="option${loop.index}_${question.getQId()}">${option.optContent}</label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${question.type.getTypeId() == 2}">
                                <%--For type_id = 2 (True/False) --%>
                                <div class="row ">
                                    <div class="answer-option">
                                        A.
                                        <input type="radio" id="optionTrue_${question.getQId()}"
                                               name="answer_${question.getQId()}" value="True">
                                        <label for="optionTrue_${question.getQId()}">True</label>
                                    </div>
                                </div>

                                <div class="row ">
                                    <div class="answer-option">
                                        B.
                                        <input type="radio" id="optionFalse_${question.getQId()}"
                                               name="answer_${question.getQId()}" value="False">
                                        <label for="optionFalse_${question.getQId()}">False</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${question.type.getTypeId() == 3}">
                                <div class="row ">
                                    <div class="answer-option">
                                        <label for="textInput_${question.getQId()}">Answer:</label>
                                        <input type="text" id="textInput_${question.getQId()}"
                                               name="answer_${question.getQId()}">
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p>Unsupported question type</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>


            <button type="submit" id="submitButton" class="btn btn-primary mt-3 mb-3">Submit Answers</button>
        </form>
        </tbody>
    </table>
</div>
</body>

<script>
    const durationString = "${requestScope.currentTest.duration}";
    const duration = parseInt(durationString, 10) * 1000 * 60;

    // Set the end time for the countdown by adding the duration to the current time
    const endTime = new Date().getTime() + duration;

    const submitButton = document.getElementById("submitButton");

    // Update the countdown every second
    const countdownInterval = setInterval(updateCountdown, 1000);

    function updateCountdown() {
        const now = new Date().getTime();
        const timeDifference = endTime - now;

        if (timeDifference <= 0) {
            // Countdown has ended, submit the form
            clearInterval(countdownInterval);
            document.getElementById("timer").innerHTML = "Countdown expired";
            submitButton.click();
        } else {
            // Calculate hours, minutes, and seconds
            const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

            // Display the countdown
            document.getElementById("timer").innerHTML =
                "Time remaining: " + hours + "h " + minutes + "m " + seconds + "s";
        }
    }

</script>
</html>
