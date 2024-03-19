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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../.././css/manageRoom/ViewRoomDetail.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>

<body>
<%--Navbar--%>
<style>

    /* Navbar styling with linear gradient background */
    .navbar {
        background: linear-gradient(-225deg, #AC32E4 0%, #7918F2 48%, #4801FF 100%);
        /* Purple linear gradient background */
    }

    .navbar-brand,
    .navbar-nav .nav-link {
        color: #ffffff !important;
    }

    .navbar-brand:hover,
    .navbar-nav .nav-link:hover {
        color: #3498db !important;
    }

    .navbar-toggler-icon {
        background-color: #ffffff;
    }

    .dropdown-menu {
        background: linear-gradient(to right, #6c5ce7, #4a40a3);
        border: none;
    }

    .dropdown-item {
        color: #ffffff !important;
    }

    .dropdown-item:hover {
        background-color: #3498db !important;
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="../../">
        <img src="../../../imagines/logo1250x1250.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Quizzicle
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class='fas fa-align-right'></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class="fas fa-home"></i> Homepage</a>
            </li>
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class='fas fa-book'></i> Your room</a>
            </li>
            <li class="nav-item active mr-3">
                <a class="nav-link" href="#"><i class='fas fa-book'></i> Your set</a>
            </li>
            <li class="nav-item mr-3">
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit"><i
                            class="fas fa-search"></i></button>
                </form>
            </li>
            <li class="nav-item mr-3">
                <a class="nav-link" href="#"><i class="fas fa-bell"></i> Notification</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user"></i> Profile
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#"><i class="fas fa-cogs"></i> Settings</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="../../logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<%--create btn added by LinhNguyen--%>
<c:if test="${requestScope.currentRoom.user.id eq requestScope.currentUser.id}">
    <div class="container mt-3">
        <a href="./test/create?roomId=${requestScope.currentRoom.roomId}" class="btn btn-primary">Create Test</a>
    </div>
</c:if>

<!-- Content -->
<div class="container mt-3">
    <div class="card bg-light-purple mb-3">
        <!-- Card Body -->
        <div class="card-body d-flex justify-content-between align-items-center">
            <!-- Information Block for Current Room -->
            <div>
                <p class="text-light fs-5 font-weight-bold">${requestScope.currentRoom.roomName}</p>
            </div>

            <div>
                <p class="text-light fs-5 font-weight-bold">Invite:
                    Quizzicle/user/room/invite?codeToJoin=${requestScope.codeToJoin}</p> <br/>
                <div>
                    Code: <input class="bg-light-purple" type="text" value="${requestScope.currentRoom.code}" readonly>
                    Password: <input class="bg-light-purple" type="password" id="passwordField"
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
            <div class="container mt-3 bg-light-purple p-3 rounded room-item">
                <!-- Card Body -->
                <div class="card-body">
                    <!-- Information Block -->
                    <div class="row">
                        <p class="font-weight-bold mb-0 text-light ml-4">${test.testName}</p>
                    </div>

                    <div class="row">
                        <p class="text-light ml-4">Test description: ${test.testDescription}</p>
                    </div>
                    <div class="row">
                        <p class="text-light ml-4">Start: ${test.startTime} | End: ${test.endTime}</p>
                    </div>
                    <div class="row">
                        <a href="./test/dotest?testId=${test.testId}" class="btn btn-primary ml-4 mb-4">Do Test</a>
                    </div>
                    <div class="row">
                        <a href="./test/leaderboard?testId=${test.testId}" class="btn btn-primary ml-4">Leader board</a>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>