<%@ page import="java.util.regex.Pattern" %><%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 1/19/2024
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="shortcut icon" type="image/x-icon" href="./icons/favicon32x32.ico"/>
    <link rel="stylesheet" href="./css/Register.css">
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                <form action="./register" method="post" class="mx-1 mx-md-4">

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="input-group form-outline flex-fill mb-0">
                                            <input type="text" name="first-name" placeholder="First Name"
                                                   class="form-control" required/>
                                            <input type="text" name="last-name" placeholder="Last Name"
                                                   class="form-control" required/>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fa-solid fa-address-card fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name="username" placeholder="Username"
                                                   class="form-control"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" name="email" placeholder="Email" class="form-control"
                                                   required/>
                                        </div>
                                    </div>


                                    <div>
                                        <div class="input-group d-flex flex-row align-items-center mb-4 position-relative">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw "></i>
                                            <span class="position-absolute top-custom start-100 translate-middle-y p-1"
                                                  id="show-hide-password">
                                                <i class="fa-regular fa-eye-slash"></i>
                                            </span>
                                            <input
                                                    type="password"
                                                    class="form-control rounded mt-1 password"
                                                    placeholder="Type your password"
                                                    aria-label="password"
                                                    aria-describedby="password"
                                                    name="password"
                                                    id="password-input"
                                            />
                                            <div class="valid-feedback">Good</div>
                                            <div class="invalid-feedback">Wrong</div>
                                        </div>
                                        <div
                                                class="alert px-4 py-3 mb-0 d-none"
                                                role="alert"
                                                data-mdb-color="warning"
                                                id="password-alert"
                                        >
                                            <ul class="list-unstyled mb-0">
                                                <li class="requirements leng">
                                                    <i class="fas fa-check text-success me-2"></i>
                                                    <i class="fas fa-times text-danger me-3"></i>
                                                    Your password must have at least 8 chars
                                                </li>
                                                <li class="requirements big-letter">
                                                    <i class="fas fa-check text-success me-2"></i>
                                                    <i class="fas fa-times text-danger me-3"></i>
                                                    Your password must have at least 1 big letter.
                                                </li>
                                                <li class="requirements num">
                                                    <i class="fas fa-check text-success me-2"></i>
                                                    <i class="fas fa-times text-danger me-3"></i>
                                                    Your password must have at least 1 number.
                                                </li>
                                                <li class="requirements special-char">
                                                    <i class="fas fa-check text-success me-2"></i>
                                                    <i class="fas fa-times text-danger me-3"></i>
                                                    Your password must have at least 1 special char.
                                                </li>
                                            </ul>
                                        </div>

                                    </div>
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="verify-password" placeholder="Verify password"
                                                   class="form-control password" required/>
                                        </div>
                                    </div>


                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg">Register</button>
                                    </div>
                                    <div class="text-center">
                                        <a href="./login" class="text-decoration-none text-primary">
                                            Already have an account?
                                        </a>
                                    </div>
                                </form>

                            </div>
                            <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                                     class="img-fluid" alt="Sample image">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="./js/Register.js"></script>
</body>
</html>
