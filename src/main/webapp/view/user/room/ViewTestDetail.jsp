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
    <link rel="stylesheet" href="../../.././css/manageRoom/ViewTestDetail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageRoom/ManageRoomScreen.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
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
            </div>

            <!-- Settings Dropdown -->
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Settings</span>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/room/test/update?testId=${requestScope.currentTest.testId}">Edit</a></li>
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
                                            <%--                                        <input type="radio" id="option${loop.index}_${question.getQId()}"--%>
                                            <%--                                               name="answer_${question.getQId()}"--%>
                                            <%--                                               value="${option.optContent}">--%>
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
                                        <%--                                    <input type="radio" id="optionTrue_${question.getQId()}"--%>
                                        <%--                                           name="answer_${question.getQId()}" value="True">--%>
                                    <label for="optionTrue_${question.getQId()}">True</label>
                                </div>
                            </div>

                            <div class="row ">
                                <div class="answer-option">
                                    B.
                                        <%--                                    <input type="radio" id="optionFalse_${question.getQId()}"--%>
                                        <%--                                           name="answer_${question.getQId()}" value="False">--%>
                                    <label for="optionFalse_${question.getQId()}">False</label>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${question.type.getTypeId() == 3}">
                            <div class="row ">
                                <div class="answer-option">
                                        <%--                                    <label for="textInput_${question.getQId()}">Answer:</label>--%>
                                        <%--                                    <input type="text" id="textInput_${question.getQId()}"--%>
                                        <%--                                           name="answer_${question.getQId()}">--%>
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
        </tbody>
    </table>
</div>


</body>
</html>
