<%--
  Created by IntelliJ IDEA.
  User: Khanh Long Tran
  Date: 2/22/2024
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <a class="btn btn-secondary mb-3" href="${pageContext.request.contextPath}/user/room/test/leaderboard?testId=${requestScope.currentTest.testId}">Back</a>
    <div class="card  mb-3">
        <!-- Card Body -->
        <div class="card-body d-flex justify-content-between align-items-center">
            <!-- Information Block for Current Room -->
            <div class=" fs-5 ">
                <a class=" fs-3 text-decoration-none" href="${pageContext.request.contextPath}/user/room/test/get?testId=${requestScope.currentTest.testId}">Title: ${requestScope.currentTest.testName}</a>
                <p>Description: ${requestScope.currentTest.testDescription}</p>
                <p>Duration: ${requestScope.currentTest.duration} (minutes)</p>
                <p>Attempt: ${requestScope.currentTest.attempt}</p>
                <fmt:formatDate value="${requestScope.currentTest.startTime}" pattern="HH:mm dd/MM/yyyy" var="formattedStartDate"/>
                <p>Start time: ${formattedStartDate}</p>
                <fmt:formatDate value="${requestScope.currentTest.endTime}" pattern="HH:mm dd/MM/yyyy" var="formattedEndDate"/>
                <p>End time: ${formattedEndDate} </p>
            </div>
        </div>
    </div>
</div>

<%--Display list question --%>
<div class="container">
    <table class="table table-striped">
        <tbody>

        <c:forEach items="${requestScope.listQuestions}" var="question">
            <script>
                console.log(${question.answer})
            </script>
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

                <!-- Column for Answers For ABCD -->
                <td>
                    <c:choose>
                        <c:when test="${question.type.getTypeId() == 1}">
                            <!-- For type_id = 1 (A to K) -->
                            <c:forEach items="${question.questionOptions}" var="option" varStatus="loop">
                                <div class="row ">
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
                            <div class="row ">
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

                            <div class="row ">
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
                            <div class="row ">
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
