<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 1/27/2024
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verify</title>
    <link rel="shortcut icon" type="image/x-icon" href="./icons/logo64x64.ico"/>
    <link rel="stylesheet" href="./css/Verify.css">
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4">Verify Your Email</h2>
    <form action="./verify-email" method="post">
        <div class="mb-3">
            <label for="verificationCode" class="form-label">Enter Verification Code</label>
            <input type="text" class="form-control" id="verificationCode" name="code" required>
        </div>
        <button type="submit" class="btn btn-primary">Verify</button>
    </form>
</div>
</body>
</html>
