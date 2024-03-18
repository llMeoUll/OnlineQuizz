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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </style>
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


<%--Navbar--%>
<style>

    /* Navbar styling with linear gradient background */
    .navbar {
        background: linear-gradient(-225deg, #AC32E4 0%, #7918F2 48%, #4801FF 100%);
        /* Purple linear gradient background */
    }

    .navbar-brand,
    .navbar-nav .nav-link {
        color: #ffffff !important;
    }

    .navbar-brand:hover,
    .navbar-nav .nav-link:hover {
        color: #3498db !important;
    }

    .navbar-toggler-icon {
        background-color: #ffffff;
    }

    .dropdown-menu {
        background: linear-gradient(to right, #6c5ce7, #4a40a3);
        border: none;
    }

    .dropdown-item {
        color: #ffffff !important;
    }

    .dropdown-item:hover {
        background-color: #3498db !important;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="../../">
        <img src="../../.././imagines/logo1250x1250.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Quizzicle
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class='fas fa-align-right'></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class="fas fa-home"></i> Homepage</a>
            </li>
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class='fas fa-book'></i> Your room</a>
            </li>
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class='fas fa-book'></i> Your set</a>
            </li>
            <li class="nav-item mr-3">
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit"><i
                            class="fas fa-search"></i></button>
                </form>
            </li>
            <li class="nav-item mr-3">
                <a class="nav-link" href="#"><i class="fas fa-bell"></i> Notification</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user"></i> Profile
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#"><i class="fas fa-cogs"></i> Settings</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="../../logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<!-- Content -->
<div class="container mt-3">
    <div class="card bg-light-purple mb-3">
        <!-- Card Body -->
        <div class="card-body d-flex justify-content-between align-items-center">
            <!-- Information Block for Current Room -->
            <div>
                <p class="text-light fs-5 font-weight-bold">${requestScope.currentTest.testName}</p>
                <p class="text-light fs-5 font-weight-bold">${requestScope.currentTest.testDescription}</p>
                <p class="text-light fs-5 font-weight-bold">Start time: ${requestScope.currentTest.startTime}
                    - End time: ${requestScope.currentTest.endTime} </p>
                <h2 id="timer" class="text-light fs-3 font-weight-bold text-center mb-4"></h2>
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
                        <div class="container mt-1 bg-light-purple p-3 rounded room-item">
                            <!-- Card Body -->
                            <div class="card-body">
                                <!-- Information Block -->
                                <div class="row">
                                    <p class="font-weight-bold mb-0 text-light ml-4">${question.question}</p>
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
                                    <div class="row text-light">
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
                                <div class="row text-light">
                                    <div class="answer-option">
                                        A.
                                        <input type="radio" id="optionTrue_${question.getQId()}"
                                               name="answer_${question.getQId()}" value="True">
                                        <label for="optionTrue_${question.getQId()}">True</label>
                                    </div>
                                </div>

                                <div class="row text-light">
                                    <div class="answer-option">
                                        B.
                                        <input type="radio" id="optionFalse_${question.getQId()}"
                                               name="answer_${question.getQId()}" value="False">
                                        <label for="optionFalse_${question.getQId()}">False</label>
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${question.type.getTypeId() == 3}">
                                <div class="row text-light">
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
