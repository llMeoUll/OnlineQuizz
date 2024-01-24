<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 1/18/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="./css/manageRoom/ViewRoomDetail.css">
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
<%--navber--%>
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
    <a class="navbar-brand" href="#">
        <img src="" width="30" height="30" class="d-inline-block align-top" alt="">
        Your Logo
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
                    <a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<!-- Content -->


<div class="container mt-4">
    <!-- Information room -->
    <div class="container mt-4 header-info-section-boundary">

        <div class="header-section">
            <div class="room-info">
                <div>
                    <h1>Room Name</h1>
                    <p>Description of the room.</p>
                </div>
                <div class="room-buttons">
                    <button type="button" class="btn btn-light">Create Test</button>
                    <button type="button" class="btn btn-light">Add Member</button>
                    <!-- Dropdown for Settings -->
                    <div class="btn-group dropdown">
                        <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                            Settings
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Delete Room</a>
                            <a class="dropdown-item" href="#">Edit Room</a>
                            <a class="dropdown-item" href="#">Remove All Members</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="info-section">
            <div>
                <div class="invite-section d-flex justify-content mb-2">
                    <label> invite: </label> <input type="text" class="form-control invite-input px-2 ml-2"
                                                    placeholder="Click to copy" readonly
                                                    onclick="copyToClipboard('.invite-input')"
                                                    style="width: 150px;">

                </div>
                <div class="code-section d-flex justify-content mb-2">
                    <label>Code: </label><input type="text" class="form-control code-input px-2 ml-2"
                                                placeholder="Click to copy" readonly
                                                onclick="copyToClipboard('.code-input')"
                                                style="width: 150px;">
                    <div class="copy-message code-message d-none">Copied!</div>

                </div>
                <p class="text-left">Number of Members: 50</p>
                <p class="text-left">Number of Tests: 10</p>
                <p class="text-left">Workplace</p>
            </div>
        </div>

    </div>

    <!-- List of tests -->
    <ul class="list-group ">
        <li class="list-group-item custom-list-group">
            <div class="d-flex justify-content-between">
                <div>
                    <h5 class="mb-1">Test 1</h5>
                    <p class="mb-1">Number of Questions: 10</p>
                    <small>Created Date: January 1, 2022</small>
                </div>
                <button type="button" class="btn btn-primary">Start Test</button>
            </div>
        </li>
        <li class="list-group-item custom-list-group">
            <div class="d-flex justify-content-between">
                <div>
                    <h5 class="mb-1">Test 2</h5>
                    <p class="mb-1">Number of Questions: 15</p>
                    <small>Created Date: January 5, 2022</small>
                </div>
                <button type="button" class="btn btn-primary">Start Test</button>
            </div>
        </li>
        <!-- Add more list items for additional tests as needed -->
    </ul>
</div>
<script>
    function copyToClipboard(selector, messageSelector) {
        var input = document.querySelector(selector);
        var copyMessage = document.querySelector(messageSelector);

        input.select();
        document.execCommand('copy');
    }

</script>

</body>

</body>

</html>

</html>