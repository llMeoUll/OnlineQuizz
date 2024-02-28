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
<%--    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">--%>
<%--    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>--%>
</head>
<body>
<form method="post" action="./verify-code">
<div class="container">
    <h2 class="text-center mb-4">Verify Your Code</h2>
    <p>
        We emailed you the six-digit code to verify your email address, please enter it below.
    </p>
    <div class="code-container">
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
        <input type="number" class="code" placeholder="0" min="0" max="9" required>
    </div>
    <input type="hidden" id="codeInput" name="code">
    <input type="hidden" name="verify-type" value="${sessionScope.verifyType}">
    <p>
        ${requestScope.error}
    </p>
    <div>
        <button type="button" id="sumbit-btn" class="btn btn-primary" onclick="submitCode()">Verify</button>
    </div>
    <small>
        If you didn't receive the code within 3 minutes, <strong>RESEND</strong>
    </small>
</div>
</form>
<script src="./js/Verify.js"></script>
</body>
</html>