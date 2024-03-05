<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/1/2024
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Test</title>
    <link rel="stylesheet" href="../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../.././css/CreateSet.css">
    <link rel="stylesheet" href="../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <form action="./create" method="post">
        <div class="row">
            <div class="col-md-12">
                <h1>Create Test</h1>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Test Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="row">
                    <div class="col-md-6 form-group">
                        <label for="start">Start time</label>
                        <input type="datetime-local" class="form-control" id="start" name="start" required>
                    </div>
                    <div class="col-md-3 form-group">
                        <label for="duration">Test duration</label>
                        <input type="number" class="form-control" id="duration" name="duration" required>
                    </div>
                    <div class="col-md-3 form-group">
                        <label for="attempt">Attempt</label>
                        <input type="number" class="form-control" id="attempt" name="attempt" required>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="description">Test description</label>
                    <textarea type="text" class="form-control" id="description" name="description" style="height: 85%" required></textarea>
                </div>
            </div>
        </div>
        <button type="submit" id="btn-next" class="btn btn-primary">Next</button>
    </form>
</div>

</div>
<script src="../../.././js/CreateTest.js"></script>
</body>
</html>
