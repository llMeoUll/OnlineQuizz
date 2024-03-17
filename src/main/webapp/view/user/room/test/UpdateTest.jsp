<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/10/2024
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Edit Test</title>
    <link rel="stylesheet" href="../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../.././css/CreateTest.css">
</head>
<body>
<form action="./update" method="post" id="review-form">
    <div class="container">
        <div class="row mb-3 text-center">
            <h3>Edit test</h3>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Test Name</label>
                    <input type="text" class="form-control" id="name" name="name" required
                           value="${requestScope.test.testName}"/>
                </div>
                <div class="row justify-content-between">
                    <div class="col-md-6 form-group">
                        <label for="start">Start time</label>
                        <input type="datetime-local" class="form-control" id="start" name="start" required
                                <c:set var="now" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd\'T\'HH:mm" var="minTime"/>
                               min="${minTime}"
                               value="${requestScope.test.startTime}"/>
                    </div>

                    <div class="col-md-4 form-group">
                        <label for="duration">Test duration (minutes)</label>
                        <input type="number" class="form-control" id="duration" name="duration" required
                               min="1"
                               value="${requestScope.test.duration}"/>
                    </div>

                </div>
                <div class="row justify-content-between">
                    <div class="col-md-6 form-group">
                        <label for="end">End time</label>
                        <input type="datetime-local" class="form-control" id="end" name="end" required
                                <c:set var="now" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd\'T\'HH:mm" var="minTime"/>
                               min="${minTime}"
                               value="${requestScope.test.endTime}"/>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="attempt">Attempt</label>
                        <input type="number" class="form-control" id="attempt" name="attempt" required
                               value="${requestScope.test.attempt}"
                               min="1" max="12"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="description">Test description</label>
                    <textarea type="text" class="form-control h-100" id="description" name="description"
                              required>${requestScope.test.testDescription}</textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row mb-3">
            <div class="col-md-4">
                <label for="total-score">Total score: </label>
                <input type="number" step="0.01" id="total-score" value="${requestScope.totalScore}"/>
                <button type="button" class="btn btn-primary" onclick="calculateScore()">Apply</button>
            </div>
        </div>
    </div>
    <div class="container">
        <c:forEach items="${requestScope.questions}" var="question" varStatus="choseLoop">

            <div class="card mb-3" id="card-${question.getQId()}">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span class="index">${choseLoop.count}</span>
                    <input type="checkbox"
                           name="question-ids"
                           id="checked-question-${question.getQId()}"
                           checked
                           onchange="calculateScore(${question.getQId()})"
                           value="${question.getQId()}"
                    />
                </div>
                <div class="card-body">
                    <p class="card-text">Question: ${question.question}</p>
                    <p class="card-text">Answer: ${question.answer}</p>
                    <c:if test="${question.questionOptions.size() ne 0}">
                        <c:forEach items="${question.getQuestionOptions()}" var="opt"
                                   varStatus="optionLoop">
                            <p>Option ${optionLoop.count}: ${opt.optContent}</p>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="card-footer">
                    <div class="input-group mb-2">
                        <span class="input-group-text">Score</span>
                        <input type="number" step="0.01" class="form-control score"
                               name="score-question-${question.getQId()}"
                               id="score-question-${question.getQId()}"
                               value="${requestScope.testQuestions.get(choseLoop.count - 1).score}" min="0" onchange="calculateTotalScore();"
                               required/>
                        <span class="input-group-text">Point</span>
                        <button class="btn btn-primary" onclick="deleteQuestionButton(${question.getQId()})">Delete
                            Question
                        </button>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <input type="hidden" value="${requestScope.test.testId}" name="testId"/>
    <input type="hidden" value="${requestScope.test.room.roomId}" name="roomId"/>
    <div class="container d-flex justify-content-between">
        <a class="btn btn-outline-primary" href="./get?testId=${requestScope.test.testId}">Cancel</a>
        <button type="submit" id="btn-next" onclick="handleSubmit()" class="btn btn-outline-primary">Update</button>
    </div>

</form>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="submit-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body text-danger">
            You can't next step! you must have at least 1 questions.
        </div>
    </div>
</div>
<script src="../../.././js/ReviewTest.js"></script>
</body>
</html>
