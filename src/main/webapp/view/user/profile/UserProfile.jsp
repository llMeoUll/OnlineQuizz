<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/25/2024
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<!-- header -->
<jsp:include page="../../../components/header.jsp"></jsp:include>
<!-- content -->
<div class="container" style="margin-top: 96px; min-height: 80vh">
    <div class="d-flex align-items-center mb-3">
        <c:set var="noAvatar" value="${pageContext.request.contextPath}/images/noImage.png"/>
        <img src="${requestScope.user.avatar ne null ? requestScope.user.avatar : noAvatar}" alt="avatar" width="64px" height="64px"
             class="rounded-circle border me-3"/>
        <span class="h3">${requestScope.user.givenName} ${requestScope.user.familyName}</span>
    </div>
    <ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="set-tab" data-bs-toggle="tab" data-bs-target="#set-tab-pane"
                    type="button" role="tab" aria-controls="set-tab-pane" aria-selected="true">Set
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="room-tab" data-bs-toggle="tab" data-bs-target="#room-tab-pane" type="button"
                    role="tab" aria-controls="room-tab-pane" aria-selected="false">Room
            </button>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="set-tab-pane" role="tabpanel" aria-labelledby="set-tab" tabindex="0">
            <div class="row row-cols-2 g-3">
                <c:choose>
                    <c:when test="${empty requestScope.listS}">
                        <p>${requestScope.user.givenName} has not set.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.listS}" var="s">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <div>
                                                ${s.getSName()}
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <div class="d-flex align-items-center">
                                                <img src="${s.getUser().getAvatar() ne null ? s.user.avatar : noAvatar}" alt="avatar" width="40px"
                                                     height="40px" class="rounded-circle me-2"/>
                                                <span>${s.getUser().getUsername()}</span>
                                            </div>
                                            <a class="btn btn-primary"
                                               href="${pageContext.request.contextPath}/user/set/get?setId=${s.getSId()}">View</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="tab-pane fade" id="room-tab-pane" role="tabpanel" aria-labelledby="room-tab" tabindex="0">
            <div class="row row-cols-2 g-3">
                <c:choose>
                    <c:when test="${empty listR}">
                        <!-- Print an alternative HTML structure if listSet is empty -->
                        <p>${requestScope.user.givenName} has not room.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${listR}" var="r">
                            <div class="col">
                                <div class="card">
                                    <a class="text-decoration-none text-black" href="${pageContext.request.contextPath}/user/room/get?roomId=${r.roomId}">
                                        <div class="card-body d-flex justify-content-between">
                                        <span>
                                                ${r.roomName}
                                        </span>
                                            <span>
                                            <i class="fa-solid fa-user"></i>
                                        </span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../../components/footer.jsp"></jsp:include>
</body>
</html>
