<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="../../css/Dashboard.css" rel="stylesheet">
        <link href="../../css/Register.css" rel="stylesheet">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <title>Dashboard</title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/logo96x96.png"/>

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
                <form action="./create" method="post">
                    <div class="w-50 container-fluid bg-white p-4 d-flex align-items-center justify-content-between sticky-top z-3">
                        <h3 class="d-inline mx-3">Create a new Set</h3>
                        <button type="submit" class="btn btn-primary mx-3">Create</button>
                    </div>
                    <div class="w-50 container d-flex flex-column my-3 z-1">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title" required/>
                            <label for="title">Title</label>
                        </div>
                        <div class="form-floating mb-3">
            <textarea type="text" class="form-control" id="description" name="description"
                      placeholder="Add a description"
                      style="height: 100px"></textarea>
                            <label for="description">Description</label>
                        </div>
                        <div class="form-floating mb-3" id="hashtag-input">
                            <input type="text" class="form-control" id="hashtag-text"
                                   placeholder="Enter hashtag (comma-separated)"/>
                            <label for="hashtag-text">Hashtags</label>
                            <div class="mt-4" id="hashtag-container"></div>
                        </div>

                        <div class="form-floating mb-3">
                            <select class="form-select" id="privacy" name="privacy">
                                <option value="public">Public</option>
                                <option value="private">Private</option>
                            </select>
                            <label for="privacy">Privacy</label>
                        </div>

                        <div id="question-list" class="d-flex flex-column">
                            <div class="card mb-3 question" id="question-container-1">
                                <div class="card-header">
                                    <div class="d-flex align-items-center justify-content-between mb-1 mx-2">
                                        <div>
                                            <span class="index">1</span>
                                        </div>
                                        <div>
                            <span role="button" class="text-primary trash-icon" onclick="handleRemoveQuestion(1)">
                                <i class="fa-solid fa-trash"></i>
                            </span>
                                        </div>
                                    </div>
                                    <div class="form-floating">
                                        <select class="form-select question-type" id="question-type-1" name="question-type-1"
                                                onchange="handleSelectType(1);">
                                            <option value="Multiple choice">Multiple choice</option>
                                            <option value="True/False">True/False</option>
                                            <option value="Essay">Essay</option>
                                        </select>
                                        <label class="question-type-label" for="question-type-1">Type</label>
                                    </div>
                                </div>

                                <div class="card-body multiple-choice" id="multiple-choice-1">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control mul-question" name="mul-question-1"
                                               id="mul-question-1"
                                               placeholder="Enter a question" required/>
                                        <label class="mul-question-label" for="mul-question-1">Question</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control mul-answer" id="mul-answer-1" name="mul-answer-1"
                                               placeholder="Enter a answer" required/>
                                        <label class="mul-answer-label" for="mul-answer-1">Answer</label>
                                    </div>
                                    <div class="opt-container" id="opt-container-1">
                                        <input type="hidden" name="number-opt-mul-1" id="number-opt-mul-1">
                                        <div class="input-group mb-3 align-items-center opt">
                                            <input type="text" name="mul-question-1-opt-1" class="form-control"
                                                   placeholder="Option 1" required/>
                                            <span class="text-primary mx-1 xmark-icon" role="button"
                                                  onclick="handleRemoveOpt(1,1);">
                                <i class="fa-solid fa-xmark"></i>
                            </span>
                                        </div>

                                    </div>
                                    <div class="text-center">
                        <span role="button" class="text-primary btn-add-opt" onclick="handleAddOpt(1);"><i
                                class="fa-solid fa-circle-plus"></i></span>
                                    </div>
                                </div>

                                <div class="card-body true-false" id="true-false-1" style="display: none;">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control tf-question" id="tf-question-1" name="tf-question-1"
                                               placeholder="Enter a question"/>
                                        <label class="tf-question-label" for="tf-question-1">Question</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <select class="form-select tf-answer" id="tf-answer-1" name="tf-answer-1">
                                            <option value="true">True</option>
                                            <option value="false">False</option>
                                        </select>
                                        <label class="tf-answer-label" for="tf-answer-1">Answer</label>
                                    </div>
                                </div>

                                <div class="card-body essay" id="essay-1" style="display: none;">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control essay-question" id="essay-question-1"
                                               name="essay-question-1" placeholder="Enter a question"/>
                                        <label class="essay-question-label" for="essay-question-1">Question</label>
                                    </div>
                                    <textarea class="form-control essay-answer" id="essay-answer-1" name="essay-answer-1"
                                              placeholder="Enter a answer"
                                              style="height: 100px"></textarea>
                                </div>
                            </div>
                            <button type="button" onclick="handleAddQuestion();" class="btn btn-outline-primary">+ADD QUESTION
                            </button>
                        </div>
                        <input type="hidden" name="number-of-question" id="number-of-question">
                        <button type="submit" class="btn btn-primary mt-3">Create</button>
                    </div>

                </form>
                <div class="toast-container position-fixed bottom-0 end-0 p-3">
                    <div id="submit-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                        <div class="toast-header">
                            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                        </div>
                        <div class="toast-body text-danger">
                            You can't create set! you must have at least 2 questions.
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
    <script src="../../js/Dashboard.js"></script>
    <script src="../../js/Register.js"></script>
    <script src="../.././js/CreateSet.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    </body>
</html>
