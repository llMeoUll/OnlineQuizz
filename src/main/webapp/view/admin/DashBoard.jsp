<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <title>DashBoard</title>
<%--        <style>--%>
<%--            <%@include file="../../../css/dashboard.css"%>--%>
<%--        </style>--%>
        <link rel="stylesheet" href="../../css/dashboard.css">
    </head>

    <body>
    <header class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container-fluid ">
            <!-- Left Side: Tên web -->
            <div class="navbar-brand">Quizzical</div>

            <!-- Middle: Navbar -->
            <ul class="navbar-nav mx-auto">
                <li class="nav-item link-header"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item link-header"><a class="nav-link" href="#">Admin Profile</a></li>
            </ul>

            <!-- Right Side: Admin Avatar -->

            <div class="avatar-sign-out navbar-brand">
                <button class="admin-avatar"></button>
                <button onclick="" class="btn btn-secondary mb-2 sign-out">Sign out</button>
            </div>
        </div>
    </header>

    <!-- Body -->
    <div class="container-fluid">
        <div class="row">

            <!-- Left Sidebar -->
            <div class="col-md-2 left-sidebar">
                <button onclick="" class="btn btn-secondary mb-3">Hello Admin</button>
                <ul class="nav flex-column">
                    <button class="left-sidebar-item">
                        <div>
                            <img src="${pageContext.request.contextPath}/view/icons/overview1x.png" alt="">
                        </div>
                        <li class="nav-item"><a class="nav-link" href="#">Overview</a></li>
                    </button>
                    <button class="left-sidebar-item">
                        <div>
                            <img src="${pageContext.request.contextPath}/view/icons/users1x.png" alt="">
                        </div>
                        <li class="nav-item"><a class="nav-link" href="#">Manage User</a></li>
                    </button>
                    <button class="left-sidebar-item">
                        <div>
                            <img src="${pageContext.request.contextPath}/view/icons/manageset1x.png" alt="">
                        </div>
                        <li class="nav-item"><a class="nav-link" href="#">Manage Set</a></li>
                    </button>
                    <button class="left-sidebar-item">
                        <div>
                            <img src="${pageContext.request.contextPath}/view/icons/manageroom1x.png" alt="">
                        </div>
                        <li class="nav-item"><a class="nav-link" href="#">Manage Room</a></li>
                    </button>
                    <button class="left-sidebar-item">
                        <div>
                            <img src="${pageContext.request.contextPath}/view/icons/question1x.png" alt="">
                        </div>
                        <li class="nav-item"><a class="nav-link" href="#">Question Bank</a></li>
                    </button>
                </ul>
            </div>

            <div class="col-md-8 middle-content">
                <div class="d-flex flex-column">
                    <div class="account-manage">
                        <div class="row">
                            <div class="col-md-10 manage-title">
                                Account management
                            </div>
                            <div class="col-md-2 manage-view">
                                <button>View Details</button>
                            </div>
                        </div>
                        <div class="row manage-overview mb-2">
                            <div class="col-md-8 account-manage-item">
                                <div></div>
                                <canvas id="activeUsersChart"></canvas>
                                <script>
                                    var numberOfActiveUser = ${requestScope.listNumberOfActiveUser};
                                </script>
                                <script>
                                    <%@include file="../../js/ChartActiveUsers.js"%>
                                </script>
                            </div>
                            <div class="col-md-3 account-manage-item list-new-account">
                                <div class="account">Account 1</div>
                                <div class="account">Account 2</div>
                                <div class="account">Account 3</div>
                                <div class="account">Account 4</div>
                                <div class="account">Account 5</div>
                                <div class="account">Account 6</div>
                                <div class="account">Account 7</div>
                                <div class="account">Account 8</div>
                                <div class="account">Account 9</div>
                                <div class="account">Account 10</div>
                                <div class="account">Account 11</div>
                                <div class="account">Account 11</div>
                                <div class="account">Account 11</div>
                                <div class="account">Account 11</div>
                            </div>
                        </div>
                    </div>
                    <div class="room-manage">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-10 manage-title">
                                    Room Management
                                </div>
                                <div class="col-md-2 manage-view">
                                    <button>View Details</button>
                                </div>
                            </div>
                        </div>
                        <div class="row manage-overview mb-2">
                            <div class="col-md-3 room-manage-item">
                                Room 1
                            </div>
                            <div class="col-md-3 room-manage-item">
                                Room 2
                            </div>
                            <div class="col-md-3 room-manage-item">
                                Room 3
                            </div>
                        </div>
                    </div>
                    <div class="set-manage">
                        <div class="row">
                            <div class="col-md-10 manage-title">
                                Set Management
                            </div>
                            <div class="col-md-2 manage-view">
                                <button>View Details</button>
                            </div>
                        </div>
                        <div class="set-manage-overview">
                            <div class="set-manage-item">

                            </div>
                            <div class="set-manage-item">

                            </div>
                            <div class="set-manage-item">

                            </div>
                            <div class="set-manage-item">

                            </div>
                            <div class="set-manage-item">

                            </div>
                            <div class="set-manage-item">

                            </div>
                        </div>
                    </div>
                    <div class="question-manage">
                        <div class="row">
                            <div class="col-md-10 manage-title">
                                Question Bank
                            </div>
                            <div class="col-md-2 manage-view">
                                <button>View Details</button>
                            </div>
                        </div>
                        <div class="set-manage-overview">
                            <table>
                                <thead>
                                <tr>
                                    <td>Question ID</td>
                                    <td>Category</td>
                                    <td>Question Content</td>
                                    <td>Question Answer</td>
                                    <td>Author</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Right Sidebar -->
            <div class="col-md-2 right-sidebar">
                <h5>Notifications</h5>
                <!-- Các thông báo nằm ở đây -->
            </div>

            <!-- Middle Content -->


        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>