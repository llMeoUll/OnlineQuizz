<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/23/2024
  Time: 2:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top shadow">
        <div class="container-fluid d-flex align-items-center">
            <a class="navbar-brand" href="${pageContext. request. contextPath}/home">
                <img src="${pageContext. request. contextPath}/images/logo1250x1250.png" width="32" height="32" class="d-inline-block align-top" alt="">
                Quizzical
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <ul class="collapse navbar-collapse m-0" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:forEach items="${sessionScope.user.roles}" var="role">
                        <c:if test="${role.getRId() eq 3}">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="${pageContext. request. contextPath}/admin/dashboard">Dashboard</a>
                            </li>
                        </c:if>
                    </c:forEach>


                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${pageContext. request. contextPath}/user/set">Your set</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext. request. contextPath}/user/room">Your room</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Create
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext. request. contextPath}/user/set/create">Create set</a></li>
                            <li><a class="dropdown-item" href="${pageContext. request. contextPath}/user/room/create">Create room</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <form class="d-flex m-0" role="search" action="${pageContext. request. contextPath}/search" method="post">
                        <input class="form-control me-2" type="search" name="query" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-primary" type="submit">Search</button>
                    </form>
                </ul>
                <c:if test="${sessionScope.user == null}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item me-3">
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/login">LOGIN</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/register">SIGN UP</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"  aria-expanded="false">
                            <span style="font-size: 1.5rem"><i class="fa-sharp fa-solid fa-bell"></i></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end overflow-y-auto" style="max-height: 400px">
                            <c:forEach items="${sessionScope.notifications}" var="notification">
                                <li>
                                    <a class="dropdown-item" href="${notification.url}">${notification.content}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="nav-item dropdown d-flex align-items-center">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <c:set var="noAvatar" value="${pageContext.request.contextPath}/images/noImage.png"/>
                            <img src="${sessionScope.user.avatar != null ? sessionScope.user.avatar : noAvatar}" alt="UserAvatar"
                                 style="width: 30px;height: 30px;border-radius: 50%">
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile">
                                <span style="font-size: 16px"><i class="fa-solid fa-user"></i></span> Profile
                            </a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile/update"><span style="font-size: 16px"><i class="fas fa-cogs"></i></span> Settings</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"> <span style="font-size: 16px"><i class="fas fa-sign-out-alt"></i></span> Logout</a></li>
                        </ul>
                    </li>
                </ul>
                </c:if>
        </div>
        </div>
    </nav>
</header>
