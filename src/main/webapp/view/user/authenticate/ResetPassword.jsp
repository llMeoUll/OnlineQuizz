<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 1/30/2024
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change your password</title>
    <link rel="shortcut icon" type="image/x-icon" href="./icons/favicon32x32.ico"/>
    <link rel="stylesheet" href="./css/ResetPassword.css">
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<section class="vh-100">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
                <div class="card text-black" style="border-radius: 25px;">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">

                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Change Password</p>

                                <form action="./reset-password" method="post" class="mx-1 mx-md-4">
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
                                            <ul class="list-unstyled mb-0 d-flex flex-column align-items-start">
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
                                    <div class="d-flex flex-row align-items-center">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="verify-password" placeholder="Verify password"
                                                   class="form-control password" required/>
                                        </div>
                                    </div>


                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="submit" class="btn btn-primary btn-lg">Save</button>
                                    </div>

                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="./js/ResetPassword.js"></script>
</body>
</html>
