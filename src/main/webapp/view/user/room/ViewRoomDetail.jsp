<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 1/18/2024
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- Đây là trang chứa thông tin chi tiết của một room, trong một room có nhiều bài test (trong một bài test có nhiều câu hỏi) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="ftm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Room" %>
<%@ page import="util.GenerateCodeToJoin" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="entity.User" %>
<%@ page import="dao.UserDBContext" %>
<%@ page import="entity.Test" %>
<%@ page import="dao.TestDBContext" %>


<html>

<head>
    <title>View list test in room</title>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageRoom/ViewRoomDetail.css">--%>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>

<!-- Content -->
<div class="container" style="margin-top: 96px">
    <h3 class="text-center">Room: ${requestScope.currentRoom.roomName}</h3>
    <!-- Button trigger modal -->
    <div class="input-group">
        <c:if test="${requestScope.joined}">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#infoRoomModal">
                Info
            </button>
        </c:if>
        <%--create btn added by LinhNguyen--%>
        <c:if test="${requestScope.currentRoom.user.id eq sessionScope.user.id}">
            <a href="./test/create?roomId=${requestScope.currentRoom.roomId}" class="btn btn-success">Create
                Test</a>
        </c:if>
        <!-- Settings Dropdown -->
        <c:if test="${requestScope.currentRoom.user.id eq sessionScope.user.id}">
            <div class="dropdown">
                <button type="button" class="btn btn-secondary dropdown-toggle rounded-0 rounded-end-2"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <span>Settings</span>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#" onclick="showEditModal()">Edit</a></li>
                    <li><a class="dropdown-item" href="#" onclick="showDeleteConfirmation()">Delete</a></li>
                </ul>
            </div>
            <!-- Edit Room Modal -->
            <div class="modal" id="editRoomModal" tabindex="-1" aria-labelledby="editRoomModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editRoomModalLabel">Edit Room</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-purple">
                            <!-- Your form or content for editing goes here -->
                            <!-- Example: -->
                            <form id="editRoomForm" action="./update" method="post">
                                <div class="mb-3">
                                    <label for="roomName" class="form-label">Room Name:</label>
                                    <input type="text" class="form-control" id="roomName" name="roomName"
                                           value="${requestScope.currentRoom.roomName}">
                                </div>
                                <div class="mb-3">
                                    <label for="description" class="form-label">Description:</label>
                                    <input type="text" class="form-control" id="description" name="description"
                                           value="${requestScope.currentRoom.description}">
                                </div>
                                <!-- Add other form fields for editing -->
                                <div class="modal-footer d-flex justify-content-center">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                    </button>
                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                </div>

                                <input type="hidden" value="${requestScope.currentRoom.roomId}" name="roomId">

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Delete Confirmation Modal -->
            <div class="modal" id="deleteConfirmationModal" tabindex="-1"
                 aria-labelledby="deleteConfirmationModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteConfirmationModalLabel">Delete Confirmation</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body text-purple">
                            Are you sure you want to delete this room?
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <a class="btn btn-danger" href="./delete?roomId=${requestScope.currentRoom.roomId}"
                               onclick="deleteRoom()">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <!-- Modal room info -->
    <div class="modal fade" id="infoRoomModal" tabindex="-1" aria-labelledby="infoRoomModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="infoRoomModalLabel">Room info</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Invite: Quizzicle/user/room/invite?codeToJoin=${requestScope.codeToJoin}</p>
                    <p>Room code: ${requestScope.currentRoom.code}</p>
                    <input type="password" id="passwordField" value="${requestScope.currentRoom.password}"
                           readonly/><span id="togglePassword" onclick="togglePasswordVisibility()">👁️</span>
                </div>
            </div>
        </div>
    </div>
</div>


<%--Display list test --%>
<c:if test="${empty requestScope.listTestOfRoom}">
    <p class="text-center fs-5">This room has no test.</p>
</c:if>
<div class="container mt-3">
    <c:forEach items="${requestScope.listTestOfRoom}" var="test">
        <a href="./test/get?testId=${test.testId}" class="text-decoration-none">
            <div class="card mb-3">
                <div class="card-body">
                    <!-- Information Block -->
                    <div class="row">
                        <p class="font-weight-bold mb-0 ml-4">${test.testName}</p>
                    </div>

                    <div class="row">
                        <p class=" ml-4">Test description: ${test.testDescription}</p>
                    </div>
                    <div class="row">
                        <ftm:formatDate value="${test.startTime}" pattern="HH:mm dd/MM/yyyy"
                                        var="formattedStartTime"/>
                        <ftm:formatDate value="${test.endTime}" pattern="HH:mm dd/MM/yyyy" var="formattedEndTime"/>
                        <p class=" ml-4">Start: ${formattedStartTime} | End: ${formattedEndTime}</p>
                    </div>
                    <div class="card-footer">
                        <c:if test="${requestScope.currentRoom.user.id ne sessionScope.user.id}">
                            <a href="${pageContext.request.contextPath}/user/room/test/dotest?testId=${test.testId}"
                               class="btn btn-primary">Do Test</a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/user/room/test/leaderboard?testId=${test.testId}"
                           class="btn btn-primary">Leader board</a>
                    </div>
                </div>
            </div>
        </a>
    </c:forEach>
    <div class="position-fixed bottom-0 start-0 p-3" style="z-index: 11">
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="mr-auto">Notification</strong>
            </div>
            <div class="toast-body">

            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/NotificationInRoom.js"></script>
<script src="${pageContext.request.contextPath}/js/ViewRoomDetail.js"></script>
</html>