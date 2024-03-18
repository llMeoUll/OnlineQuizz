<%--
  Created by IntelliJ IDEA.
  User: Khanh Long Tran
  Date: 2/22/2024
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            </div>

            <!-- Settings Dropdown -->
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Settings</span>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#" onclick="showEditModal()">Edit</a></li>
                    <li><a class="dropdown-item" href="#" onclick="showDeleteConfirmation()">Delete</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Edit Room Modal -->
<div class="modal" id="editTestModal" tabindex="-1" aria-labelledby="editTestModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editTestModalLabel">Edit Test</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-purple">
                <!-- Your form or content for editing goes here -->
                <!-- Example: -->
                <form id="editTestForm" action="./update" method="post">
                    <div class="mb-3">
                        <label for="testName" class="form-label">Test Name:</label>
                        <input type="text" class="form-control" id="testName" name="testName"
                               value="${requestScope.currentTest.testName}">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description:</label>
                        <input type="text" class="form-control" id="description" name="testDescription"
                               value="${requestScope.currentTest.testDescription}">
                    </div>
                    <!-- Add other form fields for editing -->
                    <div class="modal-footer d-flex justify-content-center">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                    <input type="hidden" value="${requestScope.currentTest.testId}" name="testId">
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmationModalLabel">Delete Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-purple">
                Are you sure you want to delete this test?
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <a class="btn btn-danger" href="./delete?testId=${requestScope.currentTest.testId}"
                   onclick="deleteTest()">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>
    function showEditModal() {
        $('#editTestModal').modal('show');
    }

    function showDeleteConfirmation() {
        $('#deleteConfirmationModal').modal('show');
    }

    function deleteTest() {
        $('#deleteConfirmationModal').modal('hide');
    }

</script>

<%--Display list question --%>
<div class="container mt-3">
    <table class="table table-striped">
        <tbody>

        <c:forEach items="${requestScope.listQuestions}" var="question">
            <script>
                console.log(${question.answer})
            </script>
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

                <!-- Column for Answers For ABCD -->
                <td>
                    <c:choose>
                        <c:when test="${question.type.getTypeId() == 1}">
                            <!-- For type_id = 1 (A to K) -->
                            <c:forEach items="${question.questionOptions}" var="option" varStatus="loop">
                                <div class="row text-light">
                                    <div class="answer-option">
                                        <c:choose>
                                            <c:when test="${loop.index == 0}">A.</c:when>
                                            <c:when test="${loop.index == 1}">B.</c:when>
                                            <c:when test="${loop.index == 2}">C.</c:when>
                                            <c:when test="${loop.index == 3}">D.</c:when>
                                            <c:when test="${loop.index == 4}">E.</c:when>
                                            <c:when test="${loop.index == 5}">F.</c:when>
                                            <c:when test="${loop.index == 6}">G.</c:when>
                                            <c:when test="${loop.index == 7}">H.</c:when>
                                            <c:when test="${loop.index == 8}">I.</c:when>
                                            <c:when test="${loop.index == 9}">J.</c:when>
                                            <c:when test="${loop.index == 10}">K.</c:when>
                                        </c:choose>

                                        <input type="radio" id="option${loop.index}_${question.getQId()}"
                                               name="answer_${question.getQId()}" value="${option.optContent}"
                                        <c:if test="${not empty listResultQuestionAnswer}">
                                        <c:forEach items="${listResultQuestionAnswer}" var="resultQuestion">
                                        <c:if test="${resultQuestion.getQId() == question.getQId() && resultQuestion.getAnswer() == option.optContent}">
                                               checked="checked"
                                        </c:if>
                                        </c:forEach>
                                        </c:if>
                                        >
                                        <label for="option${loop.index}_${question.getQId()}">${option.optContent}</label>

                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:when test="${question.type.getTypeId() == 2}">
                            <!-- For type_id = 2 (True/False) -->
                            <div class="row text-light">
                                <div class="answer-option">
                                    A.
                                    <label for="optionTrue_${question.getQId()}">True</label>
                                    <input type="radio" id="optionTrue_${question.getQId()}"
                                           name="answer_${question.getQId()}" value="True"
                                    <c:if test="${not empty listResultQuestionAnswer}">
                                    <c:forEach items="${listResultQuestionAnswer}" var="resultQuestion">
                                    <c:if test="${resultQuestion.getQId() == question.getQId() && resultQuestion.getAnswer() == 'True'}">
                                           checked="checked"
                                    </c:if>
                                    </c:forEach>
                                    </c:if>
                                    >
                                </div>
                            </div>

                            <div class="row text-light">
                                <div class="answer-option">
                                    B.
                                    <label for="optionFalse_${question.getQId()}">False</label>
                                    <input type="radio" id="optionFalse_${question.getQId()}"
                                           name="answer_${question.getQId()}" value="False"
                                    <c:if test="${not empty listResultQuestionAnswer}">
                                    <c:forEach items="${listResultQuestionAnswer}" var="resultQuestion">
                                    <c:if test="${resultQuestion.getQId() == question.getQId() && resultQuestion.getAnswer() == 'False'}">
                                           checked="checked"
                                    </c:if>
                                    </c:forEach>
                                    </c:if>
                                    >
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${question.type.getTypeId() == 3}">
                            <div class="row text-light">
                                <div class="answer-option">
                                    <label for="textInput_${question.getQId()}">Answer:</label>
                                    <c:if test="${not empty listResultQuestionAnswer}">
                                        <c:forEach items="${listResultQuestionAnswer}" var="resultQuestion">
                                            <c:if test="${resultQuestion.getQId() == question.getQId()}">
                                                <input type="text" id="textInput_${question.getQId()}"
                                                       name="answer_${question.getQId()}"
                                                       value="${resultQuestion.getAnswer()}" readonly>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>

                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                    <div class="text-success">
                        The correct answer is: ${question.answer}
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
