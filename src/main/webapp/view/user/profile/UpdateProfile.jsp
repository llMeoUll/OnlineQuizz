<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/27/2024
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UpdateProfile.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"/>
<main class="container min-vh-100 d-flex align-items-center justify-content-between">
    <div class="row justify-content-center align-items-center p-md-3 border rounded-5">
        <p class="text-center h1 mb-3">Edit Profile</p>
        <%--                                update avatar block--%>
        <div class="col-md-6 d-flex flex-column align-items-center">
            <c:set var="noAvatar"
                   value="${pageContext.request.contextPath}/images/noImage.png"/>
            <img id="avatarPreview"
                 src="${sessionScope.user.avatar != null ? sessionScope.user.avatar : noAvatar}"
                 alt="" class="rounded-circle border mb-3" height="240" width="240">
            <div class="mb-3">
                <form id="changeAvatarForm" action="update-avatar" method="GET"
                      class="d-flex align-items-center justify-content-center">
                    <input type="hidden" id="avatarUrl" name="avatarUrl">
                    <input id="fileInput" type="file" class="d-none"
                           accept=".jpg,.jpeg,.png">
                    <button type="submit" id="buttonSubmitForm"
                            class="d-none btn btn-primary">
                        Change
                    </button>
                </form>
                <button id="buttonOpenFile" onclick="changeAvatarButton()"
                        class="btn btn-primary">
                    Select Avatar
                </button>
            </div>
        </div>
        <%--                    Update given name and family name--%>
        <div class="col-md-6">
            <form class="mb-3" id="update-name-form" method="post"
                  action="${pageContext.request.contextPath}/user/profile/update-name">
                <div class="input-group">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="given-name"
                               name="given-name"
                               required
                               value="${sessionScope.user.givenName}" placeholder="John">
                        <label for="given-name">Given name</label>
                    </div>
                    <div class="form-floating">
                        <input type="text" class="form-control" id="family-name"
                               name="family-name"
                               required
                               value="${sessionScope.user.familyName}" placeholder="Doe">
                        <label for="family-name">Family name</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
                <p class="text-danger fst-italic fs-6 ps-2" id="error-name">${requestScope.errorName}</p>
            </form>
            <%--                    Update username--%>
            <form class="mb-3" id="update-username-form"
                  action="${pageContext.request.contextPath}/user/profile/update-username" method="POST">
                <div class="input-group">
                    <div class="form-floating">
                        <input type="text" class="form-control" id="username"
                               name="username"
                               required
                               value="${sessionScope.user.username}" placeholder="user1">
                        <label for="username">Username</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
                <p class="text-danger fst-italic fs-6 ps-2" id="error-username">${requestScope.errorUsername}</p>
            </form>
            <%--            Update email--%>
            <form class="mb-3" action="${pageContext.request.contextPath}/user/profile/update-email" method="post">
                <div class="input-group">
                    <div class="form-floating">
                        <input type="email" class="form-control" id="email"
                               name="email"
                               required
                               value="${sessionScope.user.email}" placeholder="user1">
                        <label for="email">Email</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
                <p class="text-danger fst-italic fs-6 ps-2" id="error-email">${requestScope.errorEmail}</p>
            </form>

            <%--            Change password--%>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#change-password-modal">
                Change password
            </button>
            <%--            modal change password--%>
            <div class="modal fade" id="change-password-modal" tabindex="-1"
                 aria-labelledby="change-password-modal-label" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="change-password-modal-label">Change password</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="update-password-form" method="POST"
                                  action="${pageContext.request.contextPath}/user/profile/update-password">
                                <div class="input-group mb-4">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="old-password"
                                               name="old-password"
                                               required
                                               placeholder="user1">

                                        <label for="old-password">Old password</label>
                                    </div>
                                </div>
                                <div class="input-group justify-content-between align-items-center mb-4">
                                    <div class="form-floating">
                                        <input type="password" class="form-control password"
                                               name="new-password"
                                               aria-label="password"
                                               aria-describedby="password"
                                               id="password-input"
                                               required
                                               placeholder="user1">
                                        <label for="password-input">New password</label>
                                    </div>
                                    <span class="p-2" id="show-hide-password">
                                                    <i class="fa-regular fa-eye-slash"></i>
                                            </span>
                                </div>
                                <div
                                        class="alert px-4 py-3 mb-0 d-none mb-3"
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
                                <div class="input-group mb-4">
                                    <div class="form-floating">
                                        <input type="password" class="form-control password" id="confirm-password"
                                               name="confirm-password"
                                               placeholder="user1"
                                               required
                                               id="confirm-password">
                                        <label for="confirm-password">Confirm password</label>
                                    </div>
                                </div>
                                <p class="text-danger fst-italic fs-6 ps-2" id="error-password"></p>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${requestScope.errorPassword != null}">
                <div class="modal" id="error-password-modal" tabindex="-1">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <p class="modal-title text-danger">${requestScope.errorPassword}</p>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</main>
<jsp:include page="../../../components/footer.jsp"/>
<script src="${pageContext. request. contextPath}/js/UpdateProfile.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.3.2/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/8.3.2/firebase-storage.js"></script>
<script src="${pageContext. request. contextPath}/js/UploadFirebase.js"></script>
</body>
</html>
