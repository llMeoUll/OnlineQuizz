<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/10/2024
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Self-test Setting</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
<%--    <link rel="stylesheet" href="../.././css/CreateSet.css">--%>
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<div class="container h-100 w-100 d-flex align-items-center justify-content-center">
    <form action="./self-test-setting" method="post" class="w-50" id="setting-form">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <div>
                    <h5 class="card-title">${sessionScope.set.getSName()}</h5>
                    <h4 class="card-text">Set up your test</h4>
                </div>
                <div>
                    <a href="./get?setId=${sessionScope.set.getSId()}" class="btn btn-outline">
                        <i class="fa-solid fa-x"></i>
                    </a>
                </div>
            </div>
            <c:set var="numberQuestion" value="${sessionScope.set.questions.size()}"/>
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <p>
                        Questions (max ${numberQuestion})
                    </p>
                    <input type="number" name="number-question" value="${numberQuestion >= 20 ? 20 : numberQuestion}" min="2"/>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <p>
                        True/False
                    </p>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" name="type-true-false" role="switch" >
                    </div>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <p>
                        Multiple Choice
                    </p>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" name="type-multiple-choice" checked role="switch" >
                    </div>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <p>
                        Essay
                    </p>
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" name="type-essay" role="switch" >
                    </div>
                </div>
            </div>
            <div class="card-footer d-flex justify-content-end">
                <button class="btn btn-primary ">Start test</button>
            </div>
        </div>
    </form>

</div>

<script src="../.././js/SelfTestSetting.js"></script>
</body>
</html>
