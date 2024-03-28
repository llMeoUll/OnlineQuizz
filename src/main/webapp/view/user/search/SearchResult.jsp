<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/25/2024
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Result</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<c:set var="noImage" value="${pageContext.request.contextPath}/images/noImage.png"/>

<main class="container" style="margin-top: 96px">
    <h3>Results for "${txtSearch}"</h3>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="all-result-tab" data-bs-toggle="tab"
                    data-bs-target="#all-result-tab-pane" type="button" role="tab" aria-controls="all-result-tab-pane"
                    aria-selected="true">All result
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="set-tab" data-bs-toggle="tab" data-bs-target="#set-tab-pane" type="button"
                    role="tab" aria-controls="set-tab-pane" aria-selected="false">Set
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="user-tab" data-bs-toggle="tab" data-bs-target="#user-tab-pane" type="button"
                    role="tab" aria-controls="user-tab-pane" aria-selected="false">User
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="room-tab" data-bs-toggle="tab" data-bs-target="#room-tab-pane" type="button"
                    role="tab" aria-controls="room-tab-pane" aria-selected="false">Room
            </button>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="all-result-tab-pane" role="tabpanel" aria-labelledby="all-result-tab"
             tabindex="0">
            <%--            set block--%>
            <div class="row py-3 border-bottom">
                <div class="col-12 d-flex justify-content-between">
                    <span class="fs-4 fw-bolder">Set</span>
                    <button class="btn btn-primary" onclick="clickSeeAllSet()">See All</button>
                </div>
            </div>
            <c:if test="${empty listSet}">
                <p class="fs-5">No set found!</p>
            </c:if>
            <div class="row row-cols-3 g-3 mt-3">
                <c:forEach items="${listSet}" begin="0" end="2" var="s">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/set/get?setId=${s.getSId()}">
                            <div class="card">
                                <div class="card-header">
                                    <p class="card-title fs-4 text-truncate">${s.getSName()}</p>
                                </div>
                                <div class="card-footer d-flex justify-content-start align-items-center">
                                    <img src="${s.user.avatar ne null ? s.user.avatar : noImage}"
                                         class="rounded-circle border"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title mx-3 fs-6 text-truncate">${s.user.username ne null ? s.user.username : s.user.email}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <%--            user block--%>
            <div class="row py-3 border-bottom">
                <div class="col-12 d-flex justify-content-between">
                    <span class="fs-4 fw-bolder">User</span>
                    <button class="btn btn-primary" onclick="clickSeeAllUser()">See All</button>
                </div>
            </div>
            <div class="row row-cols-3 g-3 mt-3">
                <c:if test="${empty listUser}">
                    <p class="fs-5">No user found!</p>
                </c:if>
                <c:forEach items="${listUser}" begin="0" end="2" var="u" varStatus="loop">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/profile?uId=${u.id}">
                            <div class="card">
                                <div class="card-header d-flex justify-content-start align-items-center">
                                    <img src="${u.avatar ne null ? u.avatar : noImage}" class="rounded-circle border"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title mx-3 fs-4 text-truncate">${u.username ne null ? u.username : u.email}</p>
                                </div>
                                <div class="card-body">
                                    <p class="d-inline"><i
                                            class="fa-solid fa-folder-open"></i> ${countSet[loop.index]}
                                        learning set
                                    </p>
                                    <p class="d-inline"><i
                                            class="fa-solid fa-user"></i> ${countRoom[loop.index]}
                                        class
                                    </p>
                                </div>

                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <%--            Room block--%>
            <div class="row py-3 border-bottom">
                <div class="col-12 d-flex justify-content-between">
                    <span class="fs-4 fw-bolder">Room</span>
                    <button class="btn btn-primary" onclick="clickSeeAllRoom()">See All</button>
                </div>
            </div>
            <c:if test="${empty listRoom}">
                <p class="fs-5">No room found!</p>
            </c:if>
            <div class="row row-cols-3 g-3 mt-3">
                <c:forEach items="${listRoom}" begin="0" end="2" var="r">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/room/get?roomId=${r.roomId}">
                            <div class="card">
                                <div class="card-header">
                                    <p class="card-title fs-4 text-truncate">${r.roomName}</p>
                                </div>
                                <div class="card-footer d-flex justify-content-start align-items-center">
                                    <img src="${r.user.avatar ne null ? r.user.avatar : noImage}"
                                         class="rounded-circle border"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title mx-3 fs-6 text-truncate">${r.user.username ne null ? r.user.username : r.user.email}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div class="tab-pane fade" id="set-tab-pane" role="tabpanel" aria-labelledby="set-tab" tabindex="0">
            <c:if test="${empty listSet}">
                <p class="fs-5">No set found!</p>
            </c:if>
            <div class="row row-cols-3 g-3 mt-3">
                <c:forEach items="${listSet}" var="s">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/set/get?setId=${s.getSId()}">
                            <div class="card">
                                <div class="card-header">
                                    <p class="card-title fs-4 text-truncate">${s.getSName()}</p>
                                </div>
                                <div class="card-footer d-flex justify-content-start align-items-center">
                                    <img src="${s.user.avatar ne null ? s.user.avatar : noImage}" class="rounded-circle"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title mx-3 fs-6 text-truncate">${s.user.username ne null ? s.user.username : s.user.email}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div class="tab-pane fade" id="user-tab-pane" role="tabpanel" aria-labelledby="user-tab" tabindex="0">
            <c:if test="${empty listUser}">
                <p class="fs-5">No user found!</p>
            </c:if>
            <div class="row row-cols-3 g-3 mt-3">
                <c:forEach items="${listUser}" var="u" varStatus="loop">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/profile?uId=${u.id}">
                            <div class="card">
                                <div class="card-header">
                                    <c:set var="noImage" value="${pageContext.request.contextPath}/images/noImage.png"/>
                                    <img src="${u.avatar ne null ? u.avatar : noImage}" class="rounded-circle"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title fs-4 text-truncate">${u.username ne null ? u.username : u.email}</p>
                                </div>
                                <div class="card-body">
                                    <p class="d-inline"><i
                                            class="fa-solid fa-folder-open"></i> ${countSet[loop.index]}
                                        learning set
                                    </p>
                                    <p class="d-inline"><i
                                            class="fa-solid fa-user"></i> ${countRoom[loop.index]}
                                        class
                                    </p>
                                </div>

                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div class="tab-pane fade" id="room-tab-pane" role="tabpanel" aria-labelledby="room-tab" tabindex="0">
            <c:if test="${empty listRoom}">
                <p class="fs-5">No room found!</p>
            </c:if>
            <div class="row row-cols-3 g-3 mt-3">
                <c:forEach items="${listRoom}" var="r">
                    <div class="col">
                        <a class="text-decoration-none"
                           href="${pageContext.request.contextPath}/user/room/get?roomId=${r.roomId}">
                            <div class="card">
                                <div class="card-header">
                                    <p class="card-title fs-4 text-truncate">${r.roomName}</p>
                                </div>
                                <div class="card-footer d-flex justify-content-start align-items-center">
                                    <img src="${r.user.avatar ne null ? r.user.avatar : noImage}"
                                         class="rounded-circle border"
                                         alt="avatar" width="48" height="48"/>
                                    <p class="card-title mx-3 fs-6 text-truncate">${r.user.username ne null ? r.user.username : r.user.email}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/SearchResult.js"></script>
</body>
</html>
