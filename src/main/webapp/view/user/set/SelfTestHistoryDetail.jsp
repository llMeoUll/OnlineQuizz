<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/17/2024
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>History detail</title>
    <link rel="stylesheet" href="../../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="../../../.././css/CreateSet.css">--%>
    <link rel="stylesheet" href="../../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h4>View self test detail | <a class="text-decoration-none" href="../.././get?setId=${requestScope.selfTest.set.getSId()}">${requestScope.selfTest.set.getSName()}</a></h4>
    <p>Result: ${requestScope.selfTest.result}/${requestScope.selfTestQuestions.size()}</p>
    <fmt:formatDate value="${requestScope.selfTest.createdAt}" pattern="HH:mm:ss dd/MM/yyyy" var="time"/>
    <p>Time record: ${time}</p>

</div>
<div class="container">
    <c:forEach items="${requestScope.selfTestQuestions}" var="selfTestQuestion" varStatus="selfTestQuestionLoop">
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex justify-content-between mb-3">
                    <span>${selfTestQuestionLoop.count}</span>
                    <span>${selfTestQuestion.question.type.typeName}</span>
                </div>
                <p class="card-text">Question: ${selfTestQuestion.question.question}</p>
            </div>
            <div class="card-body">
                <c:if test="${selfTestQuestion.question.type.typeName != 'Essay'}">
                <div class="row row-cols-2 g-3 d-flex justify-content-between">
                        <c:forEach items="${selfTestQuestion.question.questionOptions}" var="option"
                                   varStatus="optionLoop">
                            <div class="col">
                                <p
<%--                                Fill color green for answer--%>
                                <c:if test="${option.optContent == selfTestQuestion.question.answer}">
                                    class="text-center text-white bg-success border border-dark border-2 p-2 rounded-3 m-0"
                                </c:if>
                                <c:if test="${option.optContent != selfTestQuestion.question.answer}">
<%--                                Fill color red for incorrect answer--%>
                                    <c:if test="${option.optContent == selfTestQuestion.answer}">
                                        class="text-center text-white bg-danger border border-dark border-2 p-2 rounded-3 m-0"
                                    </c:if>
<%--                                Options have not bg-color--%>
                                    <c:if test="${option.optContent != selfTestQuestion.answer}">
                                        class="text-center border border-dark border-2 p-2 rounded-3 m-0"
                                    </c:if>
                                </c:if>
                                >${option.optContent}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${selfTestQuestion.question.type.typeName == 'Essay'}">
                    <div class="row row-cols-1 g-3 d-flex justify-content-between">
                        <div class="col">
                            <p class="card-text m-0">Answer: ${selfTestQuestion.question.questionOptions.get(0).optContent}</p>
                        </div>
                        <div class="col">
                            <p class="card-text m-0">Your Answer: ${selfTestQuestion.question.questionOptions.get(1).optContent}</p>
                        </div>
                    </div>
                </c:if>
            </div>
            <c:if test="${selfTestQuestion.question.type.typeName == 'Essay'}">
                <c:if test="${selfTestQuestion.question.questionOptions.get(0).optContent == selfTestQuestion.question.questionOptions.get(1).optContent}">
                    <div class="card-footer bg-success">
                    </div>
                </c:if>
                <c:if test="${selfTestQuestion.question.questionOptions.get(0).optContent != selfTestQuestion.question.questionOptions.get(1).optContent}">
                    <div class="card-footer bg-danger">
                    </div>
                </c:if>
            </c:if>
        </div>
    </c:forEach>
</div>


</body>
</html>
