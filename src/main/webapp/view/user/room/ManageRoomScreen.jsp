<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 1/18/2024
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Room" %>
<%@ page import="controller.user.room.utilities.GenerateCodeToJoin" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>


<html>

<head>
    <title>Manage room</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href=".././css/manageRoom/ManageRoomScreen.css">
    <link rel="stylesheet" href=".././css/manageRoom/SearchBar.css">

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
        <img src="../../imagines/logo1250x1250.png" width="30" height="30" class="d-inline-block align-top" alt="">
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
                    <a class="dropdown-item" href="../logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<!-- Content -->

<!-- Content -->
<div class="container mt-4">
    <div class="row">
        <!-- Left Block -->
        <div class="col-lg-4 col-md-4 col-sm-12 p-0">
            <!-- User Info Block -->
            <div class="mb-4 bg-light-purple p-3 rounded">
                <!-- Clickable User Avatar -->
                <img src="https://th.bing.com/th/id/R.d4cafb173ba792aaf336e574250b4560?rik=F6cZ58cL%2b%2f%2fcUQ&pid=ImgRaw&r=0"
                     alt="User Avatar" class="img-fluid rounded-circle clickable-avatar"
                     style="width: 50px; height: 50px;" data-toggle="modal" data-target="#avatarModal">
                <span class="ml-2 h5">${requestScope.userHasRoom.username}</span>
            </div>
        </div>

        <!-- Right Block -->
        <div class="col-lg-8 col-md-8 col-sm-12 p-0">

            <!-- Create Room Button -->
            <button type="button" class="btn btn-primary float-md-right float-sm-none" data-toggle="modal"
                    data-target="#createRoomModal">
                <i class="fas fa-plus-circle mr-1"></i>Create Room
            </button>
            <%-- Create join room--%>
            <button type="button" class="btn btn-primary float-md-right float-sm-none mr-3" data-toggle="modal"
                    data-target="#joinRoomModal">
                <i class="fas fa-plus-circle mr-1"></i>Join Room
            </button>

            <!-- Buttons for sorting and filtering -->
            <div class="btn-group float-md-right float-sm-none mr-2" role="group">
                <!-- Button for Newest -->
                <button type="button" class="btn btn-info mr-2" onclick="submitForm('newest')">
                    <i class="fas fa-calendar-alt mr-1"></i>Newest
                </button>

                <!-- Button for Oldest -->
                <button type="button" class="btn btn-info mr-2" onclick="submitForm('oldest')">
                    <i class="fas fa-calendar-alt mr-1"></i>Oldest
                </button>

                <!-- Button for Owned -->
                <button type="button" class="btn btn-info mr-2" onclick="submitForm('owned')">
                    <i class="fas fa-user mr-1"></i>Owned
                </button>

                <!-- Button for Joined -->
                <button type="button" class="btn btn-info" onclick="submitForm('joined')">
                    <i class="fas fa-users mr-1"></i>Joined
                </button>
            </div>

            <!-- Hidden form to submit button value -->
            <form id="sortingForm" method="GET" action="../user/room">
                <input type="hidden" name="selectedButton" id="selectedButton" value="">
            </form>

        </div>
    </div>
</div>

<script>
    function submitForm(buttonValue) {
        // Set the value of the hidden input field
        document.getElementById('selectedButton').value = buttonValue;
        // Submit the form
        document.getElementById('sortingForm').submit();
    }
</script>

<%--Display list room --%>
<div>
    <c:forEach items="${requestScope.listRoom}" var="room">
        <a href="./room/get?roomId=${room.roomId}" class="text-decoration-none">
            <div class="container mt-3 bg-light-purple p-3 rounded room-item">
                <!-- Information Block -->
                <div class="row">
                    <p class="font-weight-bold ml-4 text-light">Name: ${room.roomName} | Owner: ${room.user.username}</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>


<!-- Modal for Larger Image -->
<div class="modal fade" id="avatarModal" tabindex="-1" aria-labelledby="avatarModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-purple text-white">
                <h5 class="modal-title" id="avatarModalLabel">User's Larger Avatar</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <img src="https://th.bing.com/th/id/R.d4cafb173ba792aaf336e574250b4560?rik=F6cZ58cL%2b%2f%2fcUQ&pid=ImgRaw&r=0"
                     alt="User Avatar" class="img-fluid rounded-circle" style="max-width: 100%;">
            </div>
        </div>
    </div>
    <table>
        <thead>
        <tr>
            <th>Room</th>
            <th>Code</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Assuming you have already populated the roomCodes map in the servlet
            Map<Room, String> roomCodes = (HashMap<Room, String>) request.getAttribute("roomCodes");
        %>
        <%
            // Iterate through the map entries
            Set<Map.Entry<Room, String>> entrySet = roomCodes.entrySet();
            Iterator<Map.Entry<Room, String>> iterator = entrySet.iterator();

            while (iterator.hasNext()) {
                Map.Entry<Room, String> entry = iterator.next();
                Room room = entry.getKey();
                String code = entry.getValue();
        %>
        <tr>
            <td><%= room.getRoomName() %>
            </td>
            <td><%= code %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Modal for Create Room -->
<div class="modal fade" id="createRoomModal" tabindex="-1" aria-labelledby="createRoomModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-purple text-white">
                <h5 class="modal-title" id="createRoomModalLabel">Create Room</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Add your form elements for creating a room here -->
                <form action="../user/room/create" method="post">
                    <input type="hidden" name="ownerUser" value="${requestScope.userHasRoom.id}">
                    <div class="form-group">
                        <label for="roomName" class="text-purple">Room Name</label>
                        <input type="text" class="form-control" id="roomName" name="roomName"
                               placeholder="Enter room name" required>
                    </div>

                    <div class="form-group">
                        <label for="password" class="text-purple">Password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter password">
                    </div>

                    <div class="form-group">
                        <label for="description" class="text-purple">Description</label>
                        <textarea class="form-control" id="description" name="description"
                                  placeholder="Enter room description" rows="3"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Create Room</button>
                    <%--                    <div class="alert alert-danger" role="alert">--%>
                    <%--                        ${requestScope.errorDuplicateRoomName}--%>
                    <%--                    </div>--%>
                </form>

            </div>
        </div>
    </div>
</div>


<!-- Modal for Join Room -->
<div class="modal fade" id="joinRoomModal" tabindex="-1" aria-labelledby="joinRoomModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-purple text-white">
                <h5 class="modal-title" id="joinRoomModalLabel">Join Room</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Add your form elements for creating a room here -->
                <form action="../user/room/invite" method="post">
                    <!-- requestScope.userHasRoom.Id đại diện cho user logged trong session -->
                    <input type="hidden" name="ownerUser" value="${requestScope.userHasRoom.id}">
                    <div class="form-group">
                        <label for="code" class="text-purple">Code: </label>
                        <input type="text" class="form-control" id="code" name="code"
                               placeholder="Enter room name" required autofocus>
                    </div>

                    <div class="form-group">
                        <label for="password" class="text-purple">Password: </label>
                        <input type="password" class="form-control" name="passwordForJoining"
                               placeholder="Enter password">
                    </div>

                    <button type="submit" class="btn btn-primary">Join</button>
                </form>

            </div>
        </div>
    </div>
</div>

<script>
    // Add click event to the clickable avatar using jQuery
    $('.clickable-avatar').on('click', function () {
        // Trigger the modal to show
        $('#avatarModal').modal('show');
    });
</script>

<script src=".././js/RoomScreen/script.js"></script>

</body>


</html>