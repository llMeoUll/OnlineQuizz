<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 1/19/2024
  Time: 11:00 PM
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
</head>
<body>
<!-- Navbar -->
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
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</html>
