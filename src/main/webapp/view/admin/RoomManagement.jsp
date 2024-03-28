<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="../css/Dashboard.css" rel="stylesheet">
        <link href="../css/UserManagement.css" rel="stylesheet">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo96x96.png"/>
        <title>Dashboard</title>
    </head>
    <body class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/admin/dashboard">
            <img src="${pageContext.request.contextPath}/images/logo96x96.png" alt="Quizzicle Logo" width="30" height="30" class="d-inline-block align-text-top me-2">
            Quizzicle
        </a>
        <!-- Sidebar Toggle-->
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        <!-- Navbar-->
        <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="">Settings</a></li>
                    <li><a class="dropdown-item" href="">Activity Log</a></li>
                    <li><hr class="dropdown-divider" /></li>
                    <li><a class="dropdown-item" href="/Quizzicle/logout">Logout</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <div class="sb-sidenav-menu-heading">Core</div>
                        <a class="nav-link" href="/Quizzicle/admin/dashboard">
                            <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                            Dashboard
                        </a>
                        <div class="sb-sidenav-menu-heading">Interface</div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fa-regular fa-address-card"></i></div>
                            Users
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/Quizzicle/admin/user">Users Information</a>
                                <a class="nav-link" href="/Quizzicle/admin/user/create">Create New User</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseRooms" aria-expanded="false" aria-controls="collapseRooms">
                            <div class="sb-nav-link-icon"><i class="fa-brands fa-leanpub"></i></div>
                            Rooms
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseRooms" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/Quizzicle/admin/room">List Room</a>
                                <a class="nav-link" href="/Quizzicle/admin/room/create">Create New Room</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSets" aria-expanded="false" aria-controls="collapseSets">
                            <div class="sb-nav-link-icon"><i class="fa-brands fa-leanpub"></i></div>
                            Sets
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseSets" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/Quizzicle/admin/set">Set List</a>
                                <a class="nav-link" href="/Quizzicle/admin/set/create">Create New Set</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseQuestions" aria-expanded="false" aria-controls="collapseQuestions">
                            <div class="sb-nav-link-icon"><i class="fa-brands fa-leanpub"></i></div>
                            Question Bank
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseQuestions" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/Quizzicle/admin/question">Question List</a>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="sb-sidenav-footer">
                    <div class="small">Logged in as:</div>
                    Administrator
                </div>
            </nav>
        </div>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid mt-5">
                    <form action="../admin/room" method="POST" class="row g-3">
                        <div class="col-md-4">
                            <label for="name" class="form-label">Room Name</label>
                            <input type="text" class="form-control" id="name" name="roomName">
                        </div>
                        <div class="col-md-2">
                            <label for="fromDate" class="form-label">From Date</label>
                            <input type="datetime-local" class="form-control" id="fromDate" name="fromDate">
                        </div>
                        <div class="col-md-2">
                            <label for="toDate" class="form-label">To Date</label>
                            <input type="datetime-local" class="form-control" id="toDate" name="toDate">
                        </div>
                        <div class="col-md-2 mt-5">
                            <button type="submit" class="btn btn-primary">Filter</button>
                        </div>
                    </form>
                </div>
                <section class="vh-100" style="background-color: #eee;">
                    <div class="container py-5 h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col col-xl-10">
                                <c:forEach items="${requestScope.rooms}" var="room">
                                    <div class="card mb-5" style="border-radius: 15px;">
                                        <div class="card-body p-4">
                                            <h3 class="mb-3">${room.getRoomName()}</h3>
                                            <p class="small mb-0"><i class="fas fa-star fa-lg text-warning"></i>
                                                |</span> Created by <strong>${room.user.givenName} ${room.user.familyName}</strong> at ${room.createdAt}
                                            </p>
                                            <hr class="my-4">
                                            <div class="d-flex justify-content-start align-items-center">
                                                <p class="mb-0 text-uppercase"><i class="fas fa-cog me-2"></i> <span
                                                        class="text-muted small">
                                                    <a href="/Quizzicle/admin/room/delete?room_id=${room.roomId}">Delete</a>
                                                </span></p>
                                                <p class="mb-0 text-uppercase"><i class="fas fa-link ms-4 me-2"></i> <span
                                                        class="text-muted small">
                                                    <a href="/Quizzicle/admin/room/update?room_id=${room.roomId}">Update</a>
                                                </span></p>
                                                <span class="ms-3 me-4">|</span></p>
                                                <a href="/Quizzicle/admin/user/profile?uid=${room.user.id}">
                                                    <img src="${room.user.avatar}" alt="avatar"
                                                         class="img-fluid rounded-circle me-1" width="35">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </div>
        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <strong class="mr-auto">Notification</strong>
                </div>
                <div class="toast-body">
                    New User registered! Click to view details.
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="../js/Dashboard.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    </body>
</html>
