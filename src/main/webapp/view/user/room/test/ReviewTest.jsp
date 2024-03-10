<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/8/2024
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review test</title>
    <link rel="stylesheet" href="../../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../../.././css/CreateTest.css">
</head>
<body>
<form action="./review" method="post" id="review-form">
    <div class="container">
        <div class="row mb-3 text-center">
            <h1>Review test</h1>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Test Name</label>
                    <input type="text" class="form-control" id="name" name="name" required
                           value="${sessionScope.test.testName}"/>
                </div>
                <div class="row justify-content-between">
                    <div class="col-md-6 form-group">
                        <label for="start">Start time</label>
                        <input type="datetime-local" class="form-control" id="start" name="start" required
                                <c:set var="now" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd\'T\'HH:mm" var="minTime"/>
                               min="${minTime}"
                               value="${sessionScope.test.startTime}"/>
                    </div>

                    <div class="col-md-4 form-group">
                        <label for="duration">Test duration (minutes)</label>
                        <input type="number" class="form-control" id="duration" name="duration" required
                               min="1"
                               value="${sessionScope.test.duration}"/>
                    </div>

                </div>
                <div class="row justify-content-between">
                    <div class="col-md-6 form-group">
                        <label for="end">End time</label>
                        <input type="datetime-local" class="form-control" id="end" name="end" required
                                <c:set var="now" value="<%=new java.util.Date()%>"/>
                                <fmt:formatDate value="${now}" pattern="yyyy-MM-dd\'T\'HH:mm" var="minTime"/>
                               min="${minTime}"
                               value="${sessionScope.test.endTime}"/>
                    </div>
                    <div class="col-md-4 form-group">
                        <label for="attempt">Attempt</label>
                        <input type="number" class="form-control" id="attempt" name="attempt" required
                               value="${sessionScope.test.attempt}"
                               min="1" max="12"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="description">Test description</label>
                    <textarea type="text" class="form-control h-100" id="description" name="description"
                              required>${sessionScope.test.testDescription}</textarea>
                </div>
            </div>
        </div>
    </div>
    <c:set var="totalScore" value="10"/>
    <c:set var="totalQuestion" value="0"/>
    <c:forEach items="${sessionScope.questions}" var="choseQuestion">
        <c:set var="totalQuestion" value="${totalQuestion + 1}"/>
    </c:forEach>
    <fmt:formatNumber value="${totalScore / totalQuestion}" var="scorePerQuestion" maxFractionDigits="2"/>
    <div class="container">
        <div class="row mb-3">
            <div class="col-md-4">
                <label for="total-score">Total score: </label>
                <input type="number" step="0.01" id="total-score" value="${totalScore}"/>
                <button type="button" class="btn btn-primary" onclick="calculateScore()">Apply</button>
            </div>
        </div>
    </div>
    <div class="container">
        <c:forEach items="${sessionScope.questions}" var="choseQuestion" varStatus="choseLoop">
            <c:forEach items="${sessionScope.sets}" var="set">
                <c:forEach items="${set.getQuestions()}" var="question">
                    <c:if test="${question.getQId() eq choseQuestion}">
                        <div class="card mb-3" id="card-${choseQuestion}">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <span class="index">${choseLoop.count}</span>
                                <input type="checkbox"
                                       name="question-ids"
                                       id="checked-question-${choseQuestion}"
                                       checked
                                       onchange="calculateScore(${choseQuestion})"
                                       value="${choseQuestion}"
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
                                           name="score-question-${choseQuestion}"
                                           id="score-question-${choseQuestion}"
                                           value="${scorePerQuestion}" min="0" onchange="calculateTotalScore();"
                                           required/>
                                    <span class="input-group-text">Point</span>
                                    <button class="btn btn-primary" onclick="deleteQuestionButton(${choseQuestion})">Delete Question</button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </c:forEach>
    </div>
    <div class="container-fluid d-flex justify-content-between fixed-bottom pb-3 px-5">
        <a href="./add-question" class="btn btn-outline-primary">Back</a>
        <button type="submit" id="btn-next" onclick="handleSubmit()" class="btn btn-outline-primary">Assign</button>
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
<script src="../../../.././js/ReviewTest.js"></script>
</body>
</html>
