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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageRoom/ViewRoomDetail.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>

<!-- Content -->
<div class="container" style="margin-top: 96px">
    <%--create btn added by LinhNguyen--%>
    <c:if test="${requestScope.currentRoom.user.id eq requestScope.currentUser.id}">
        <div class="container mb-3">
            <a href="./test/create?roomId=${requestScope.currentRoom.roomId}" class="btn btn-primary">Create Test</a>
        </div>
    </c:if>
    <div class="card mb-3">
        <!-- Card Body -->
        <div class="card-body d-flex justify-content-between align-items-center">
            <!-- Information Block for Current Room -->
            <div>
                <p class=" fs-5 font-weight-bold">${requestScope.currentRoom.roomName}</p>
            </div>

            <div>
                <p class=" fs-5 font-weight-bold">Invite:
                    Quizzicle/user/room/invite?codeToJoin=${requestScope.codeToJoin}</p> <br/>
                <div>
                    Code: <input type="text" value="${requestScope.currentRoom.code}" readonly>
                    Password: <input type="password" id="passwordField"
                                     value="${requestScope.currentRoom.password}"
                                     readonly>
                    <span id="togglePassword" onclick="togglePasswordVisibility()">👁️</span>
                </div>

                <script>
                    function togglePasswordVisibility() {
                        var passwordField = document.getElementById("passwordField");
                        var toggleButton = document.getElementById("togglePassword");

                        if (passwordField.type === "password") {
                            passwordField.type = "text";
                            toggleButton.textContent = "👁️";
                        } else {
                            passwordField.type = "password";
                            toggleButton.textContent = "👁️";
                        }
                    }
                </script>

            </div>

            <!-- Settings Dropdown -->
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <span class="visually-hidden">Settings</span>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#" onclick="showEditModal()">Edit</a></li>
                    <li><a class="dropdown-item" href="#" onclick="showDeleteConfirmation()">Delete</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>


<!-- Edit Room Modal -->
<div class="modal" id="editRoomModal" tabindex="-1" aria-labelledby="editRoomModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editRoomModalLabel">Edit Room</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>

                    <input type="hidden" value="${requestScope.currentRoom.roomId}" name="roomId">

                </form>
            </div>
        </div>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmationModalLabel">Delete Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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

<script>
    function showEditModal() {
        $('#editRoomModal').modal('show');
    }

    function showDeleteConfirmation() {
        $('#deleteConfirmationModal').modal('show');
    }

    function deleteRoom() {
        $('#deleteConfirmationModal').modal('hide');
    }

</script>

<%--Display list test --%>
<div class="container mt-3">
    <c:forEach items="${requestScope.listTestOfRoom}" var="test">
        <a href="./test/get?testId=${test.testId}" class="text-decoration-none">
            <div class="container mt-3 p-3 rounded room-item">
                <div class="card">
                    <div class="card-body">
                        <!-- Information Block -->
                        <div class="row">
                            <p class="font-weight-bold mb-0 ml-4">${test.testName}</p>
                        </div>

                        <div class="row">
                            <p class=" ml-4">Test description: ${test.testDescription}</p>
                        </div>
                        <div class="row">
                            <p class=" ml-4">Start: ${test.startTime} | End: ${test.endTime}</p>
                        </div>
                    <div class="card-footer">
                        <a href="./test/dotest?testId=${test.testId}" class="btn btn-primary">Do Test</a>
                        <a href="./test/leaderboard?testId=${test.testId}" class="btn btn-primary">Leader board</a>
                    </div>
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
<script src="../.././js/NotificationInRoom.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>

</html>