<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/10/2024
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Self Test | ${sessionScope.set.getSName()}</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="../.././css/CreateSet.css">--%>
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container my-3">
    <div class="row row-cols-3 justify-content-between text-center">
        <div class="col">
            <a href="./self-test-setting?setId=${sessionScope.set.getSId()}" class="btn btn-outline-primary">Back</a>
        </div>
        <span>Self test for ${sessionScope.set.getSName()}</span>
        <span>Number of question: ${sessionScope.questions.size()}</span>
    </div>
</div>

<div class="container mb-3">
    <form action="./self-test" method="post">
        <c:forEach items="${sessionScope.questions}" var="question" varStatus="questionLoop">
            <div class="card mb-3">
                <div class="card-header">
                    <div class="d-flex justify-content-between mb-3">
                        <span>${questionLoop.index + 1}</span>
                        <span>${question.type.typeName}</span>
                    </div>
                    <p class="card-text">Question: ${question.question}</p>
                </div>
                <div class="card-body">
                    <c:if test="${question.questionOptions.size() > 0}">
                        <div class="row row-cols-2 g-3 d-flex justify-content-between"
                             id="option-container-${question.getQId()}">
                            <c:forEach items="${question.questionOptions}" var="option" varStatus="optionLoop">
                                <div class="col">
                                    <input type="text"
                                           class="btn btn-outline-primary w-100"
                                           id="question-${question.getQId()}-option-${optionLoop.count}"
                                           value="${option.optContent}"
                                           readonly
                                           onclick="handleChooseMultiplechoiceQuestion(${question.getQId()}, ${optionLoop.count})"/>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${question.type.typeName == 'Essay'}">
                        <div class="row">
                            <div class="col">
                                <textarea class="form-control" rows="10" name="answer-${question.getQId()}"></textarea>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </c:forEach>
        <div class="row">
            <input type="hidden" name="numberOfQuestion" value="${sessionScope.questions.size()}"/>
            <input type="submit" class="btn btn-primary" value="Submit"/>
        </div>
    </form>

</div>

<script src="../.././js/SelfTest.js"></script>
</body>
</html>
