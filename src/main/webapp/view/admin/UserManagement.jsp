<%-- Created by IntelliJ IDEA. User: luulo Date: 1/15/2024 Time: 10:08 AM To change this template use File | Settings |
    File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="../css/usermanagement.css">
        <title>User Management</title>
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
                            <img src="${pageContext.request.contextPath}/view/icons/manageroom1x.png"
                                 alt="">
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
                <div class="search-create row">
                    <form action="../admin/user_management" method="post">
                        <input type="text" placeholder="Input email to search" name="user_email">
                        <input type="submit" value="Search">
                    </form>
                    <button>Create new User</button>
                </div>
                <table>
                    <thead>
                    <tr>
                        <td>User ID</td>
                        <td>User Email</td>
                        <td>Username</td>
                        <td>Given Name</td>
                        <td>Family Name</td>
                        <td>Avatar</td>
                        <td>Created At</td>
                        <td>Updated At</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.email}</td>
                            <td>${user.username}</td>
                            <td>${user.givenName}</td>
                            <td>${user.familyName}</td>
                            <td>${user.picture}</td>
                            <td>${user.createdAt}</td>
                            <td>${user.updatedAt}</td>
                            <td>
                                <button onclick="deleteUser(${user.id})">
                                    Delete
                                </button>
                            </td>
                            <td>
                                <button onclick="updateUser(${user.id})">
                                    Update
                                </button>
                            </td>
                            <td>
                                <button>View Profile</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Right Sidebar -->
            <div class="col-md-2 right-sidebar">
                <h5>Notifications</h5>
                <!-- Các thông báo nằm ở đây -->
            </div>


        </div>
    </div>
    <script src="../js/ClickButton.js"></script>
    <script src="../js/ClickButton.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>