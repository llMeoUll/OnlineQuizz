<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 1/16/2024
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo96x96.png"/>
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/Login.css">
    <link rel="stylesheet" href="webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<section class="vh-100">
    <div class="container-fluid">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form method="post" action="./login">
                    <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                        <p class="lead fw-normal mb-0 me-3">Sign in with</p>
                        <a href="./login-with-google" role="button" class="btn btn-link">
                            <i class="fa-brands fa-google"></i>
                        </a>
                    </div>

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Or</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="email" class="form-control form-control-lg"
                               placeholder="Enter your email address" name="email" required}/>

                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3 position-relative">
                        <input type="password" id="password" class="form-control form-control-lg"
                               placeholder="Enter your password" name="password" required/>
                        <span class="position-absolute top-50 start-100 translate-middle-y p-2" id="show-hide-password">
                            <i class="fa-regular fa-eye-slash"></i>
                        </span>

                    </div>
                    <%--display eror message--%>
                    <p class="text-danger">
                        ${requestScope.error}
                    </p>
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="./forgot-password" class="text-body">Forgot password?</a>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Login
                        </button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">
                            Don't have an account? <a href="./register" class="link-danger">Register</a>
                        </p>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
<script src="./js/Login.js"></script>
</body>
</html>
