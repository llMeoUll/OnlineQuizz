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
        <title>Dashboard</title>
    </head>
    <body class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Navbar Brand-->
        <a class="navbar-brand ps-3" href="">
            <img src="../imagines/logo1250x1250.png" alt="Quizzicle Logo" width="30" height="30" class="d-inline-block align-text-top me-2">
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
                <div class="container-fluid px-4">
                    <div class="container mt-3 mb-4">
                        <div class="col-lg-9 mt-4 mt-lg-0">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                                        <table class="table manage-candidates-top mb-0">
                                            <thead>
                                            <tr>
                                                <th>User Name</th>
                                                <th class="text-center">Verification</th>
                                                <th class="action text-right">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.users}" var="user">
                                                <tr class="candidates-list">
                                                    <td class="title">
                                                        <div class="thumb">
                                                            <img class="img-fluid" src="${user.avatar}" alt>
                                                        </div>
                                                        <div class="candidate-list-details">
                                                            <div class="candidate-list-info">
                                                                <div class="candidate-list-title">
                                                                    <h5 class="mb-0"><a href="/Quizzicle/admin/user/profile?uid=${user.id}">${user.givenName}
                                                                            ${user.familyName}</a></h5>
                                                                </div>
                                                                <div class="candidate-list-option">
                                                                    <ul class="list-unstyled">
                                                                        <li><i
                                                                                class="fas fa-filter pr-1"></i>${user.createdAt}
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="candidate-list-favourite-time text-center">
                                                        <a class="candidate-list-favourite order-2 text-danger"
                                                           href="#"><i class="fas fa-heart"></i></a>
                                                        <span class="candidate-list-time order-1">${user.isVerified() ? "Verified" : "Not Verified"}</span>
                                                    </td>
                                                    <td>
                                                        <ul class="list-unstyled mb-0 d-flex justify-content-end">
                                                            <li><a href="/Quizzicle/admin/user/profile?uid=${user.id}"
                                                                   class="text-primary" data-toggle="tooltip" title
                                                                   data-original-title="view"><i
                                                                    class="far fa-eye"></i></a></li>
                                                            <li><a href="/Quizzicle/admin/user/update?uid=${user.id}"
                                                                   class="text-info" data-toggle="tooltip" title
                                                                   data-original-title="Edit"><i
                                                                    class="fas fa-pencil-alt"></i></a>
                                                            </li>
                                                            <li><a href="/Quizzicle/admin/user/delete?uid=${user.id}"
                                                                   class="text-danger" data-toggle="tooltip" title
                                                                   data-original-title="Delete"><i
                                                                    class="far fa-trash-alt"></i></a>
                                                            </li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="text-center mt-3 mt-sm-3">
                                            <ul class="pagination justify-content-center mb-0">
                                                <li class="page-item disabled"> <span class="page-link">Prev</span>
                                                </li>
                                                <li class="page-item active" aria-current="page"><span
                                                        class="page-link">1 </span> <span
                                                        class="sr-only">(current)</span></li>
                                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                <li class="page-item"><a class="page-link" href="#">25</a></li>
                                                <li class="page-item"> <a class="page-link" href="#">Next</a> </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
