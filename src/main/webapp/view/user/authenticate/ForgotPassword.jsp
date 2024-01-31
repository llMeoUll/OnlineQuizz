<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 1/30/2024
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/ForgotPassword.css">
    <link rel="stylesheet" href="webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="forgot-password-form">
        <h2 class="text-center mb-4">Forgot Your Password?</h2>
        <p class="text-muted text-center">Enter your email address below and we'll send you a link to reset your password.</p>
        <form method="post" action="./forgot-password">
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <p>
                ${requestScope.error}
            </p>
            <button type="submit" class="btn btn-primary btn-block">Reset Password</button>
        </form>
        <div class="text-center mt-3">
            <a href="./login" class="btn btn-link text-decoration-none">Login</a>
            <span class="mx-2">or</span>
            <a href="./register" class="btn btn-link text-decoration-none">Register</a>
        </div>
    </div>
</div>



<script src="./js/ForgotPassword.js"></script>
</body>
</html>
