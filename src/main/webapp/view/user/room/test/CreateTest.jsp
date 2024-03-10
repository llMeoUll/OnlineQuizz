<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/1/2024
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Test</title>
    <link rel="stylesheet" href="../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../.././css/CreateSet.css">
    <link rel="stylesheet" href="../../.././css/CreateTest.css">
    <link rel="stylesheet" href="../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<form action="./create" method="post">
    <div class="container">
        <div class="row mb-3">
            <div class="col-md-12 text-center">
                <h1>Create Test</h1>
            </div>
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
                    <textarea type="text" class="form-control" id="description" name="description" style="height: 85%"
                              required>${sessionScope.test.testDescription}</textarea>
                </div>
            </div>
        </div>
        <div class="container-fluid d-flex justify-content-between pb-3 px-5">
            <a href="./create/cancel" class="btn btn-outline-primary">Cancel</a>
            <button type="submit" id="btn-next" class="btn btn-outline-primary">Next</button>
        </div>
    </div>
</form>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="submit-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body text-danger">
            You can't create set! you must have at least 2 questions.
        </div>
    </div>
</div>
<script src="../../.././js/CreateTest.js"></script>
</body>
</html>
