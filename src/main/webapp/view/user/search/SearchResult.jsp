<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/25/2024
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="./css/HomePage.css">
    <style>
        .nav-link {
            color: black !important;
        }

        .nav-link:hover {
            color: rgb(40, 110, 231) !important;
        }

        /*body {*/
        /*    display: flex;*/
        /*    min-height: 100vh;*/
        /*    margin: 0;*/
        /*    !* align-items: center; *!*/
        /*    justify-content: center;*/
        /*}*/

        /*main {*/
        /*    width: 100%;*/
        /*    !* hoặc giá trị mong muốn *!*/
        /*    max-width: 800px;*/
        /*    !* hoặc giá trị mong muốn *!*/
        /*}*/

        .row {
            display: flex;
            flex-wrap: wrap;
            margin: -5px;
            /* Đảm bảo giữa các col có khoảng cách */
        }

        .col-md-4 {
            flex: 0 0 33.333333%;
            max-width: 33.333333%;
            padding: 5px;
            /* Tạo khoảng cách giữa các col */
        }

        .card {
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .card img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            float: left;
            margin-right: 10px;
            margin-left: 16px;
            margin-top: 10px;
        }

        .card-body {
            padding: 20px;
        }

        .card-title {
            font-size: 1.25rem;
            margin-bottom: 10px;
        }

        .card-subtitle {
            margin-top: 20px;
            font-size: 0.7rem;
        }

        .card-subtitle p {
            margin: 5px 10px 5px 0;
            padding: 8px;
            border: 2px solid #ccc;
            border-radius: 5px;
            background-color: #f0f0f0;
            float: left;
        }
    </style>
</head>

<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<main>
    <div class="SearchResultPage">
        <section id="SearchResultPageHeader-mainExperiment">
            <div class="SearchResultPageHeader-container">
                <div class="SearchResultPageHeader-queryFeedback">
                    <div class="SearchResultsPageHeader-resultsFor" id="SearchResultsPageHeader-resultsFor"><b>Results
                        for "${txtSearch}"</b>>
                    </div>
                </div>
                <div class="SearchResultsPageHeader-subNavExperiment" id="SearchResultsPageHeader-subNavItem">
                    <div class="TabsWrapper">
                        <ul class="nav nav-tabs mb-3" id="myTab0" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="home-tab0"
                                        data-mdb-target="#allresults" type="button" role="tab" aria-controls="home"
                                        aria-selected="true">
                                    All results
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="profile-tab0" data-mdb-target="#set"
                                        type="button" role="tab" aria-controls="profile" aria-selected="false">
                                    Set
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="contact-tab0" data-mdb-target="#user"
                                        type="button" role="tab" aria-controls="contact" aria-selected="false">
                                    User
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="room-tab0" data-mdb-target="#room"
                                        type="button" role="tab" aria-controls="contact" aria-selected="false">
                                    Room
                                </button>
                            </li>
                        </ul>
                        <div class="tab-content" id="myTabContent0">
                            <div class="tab-pane fade show active" id="allresults" role="tabpanel"
                                 aria-labelledby="home-tab0">
                                <!-- người dùng -->
                                <div class="container">
                                    <!-- Div row chứa các thành phần -->
                                    <div class="row mb-3 border-bottom pb-3">
                                        <!-- Div row đầu tiên chứa text và button -->
                                        <div class="row">
                                            <div class="col-md-9">
                                                <h4 class="mb-0">Users
                                                </h4>
                                            </div>
                                            <div class="col-md-3 text-right" id="seeAllButton">
                                                <button class="btn btn-primary ml-auto">See All</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Div row chứa 2 card -->
                                    <c:forEach items="${listUser}" begin="0" end="1" var="u" varStatus="loop">
                                        <div class="row" >
                                            <div class="col-md-6" style="margin-top: 5px">
                                                <div class="card">
                                                    <img src="${u.avatar}" class="card-img-top" alt="avatar"/>
                                                    <div class="card-body">
                                                        <h5 class="card-title">${u.username}</h5>
                                                        <h5 class="card-subtitle">
                                                            <p class="d-inline"><i
                                                                    class="fa-solid fa-folder-open"></i> ${countSet[loop.index]}
                                                                set
                                                            </p>
                                                            <p class="d-inline"><i
                                                                    class="fa-solid fa-user"></i> ${countRoom[loop.index]}
                                                                room
                                                            </p>
                                                        </h5>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- set -->
                                <div class="container" style="margin-top: 10px;">
                                    <!-- Div row chứa các thành phần -->
                                    <div class="row mb-3 border-bottom pb-3">
                                        <!-- Div row đầu tiên chứa text và button -->
                                        <div class="row">
                                            <div class="col-md-9">
                                                <h4 class="mb-0">Set</h4>
                                            </div>
                                            <div class="col-md-3 text-right" id="seeAllButton2">
                                                <button class="btn btn-primary ml-auto">See All</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Div row chứa 2 card -->
                                    <c:forEach items="${requestScope.listSet}" begin="0" end="2" var="s">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="card" style="border: 1px solid #ccc;
                                            border-radius: 5px;
                                            padding: 10px;
                                            max-width: 300px;
                                            margin: 10px">
                                                    <div class="card-content" style="display: flex;
                                                flex-direction: column;">
                                                        <h5 class="card-title" style="font-size: 18px;
                                                    margin-bottom: 10px;">${s.getSName()}</h5>
                                                        <div class="card-details" style="  display: flex;
                                                    align-items: center;">
                                                            <img src="${s.getUser().getAvatar()}" alt="Image"
                                                                 class="circular-image"
                                                                 style="  width: 30px;
                                                        height: 30px;
                                                        border-radius: 50%;
                                                        margin-right: 10px;"/>
                                                            <p class="card-text" style=" flex: 1;
                                                        margin-right: 10px;
                                                        margin-top: 10px;">${s.getUser().getUsername()}</p>
                                                            <button class="btn" style=" padding: 8px 10px;
                                                        background-color: #007bff;
                                                        color:white;">Xem trước
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- room -->
                                <div class="container" style="margin-top: 10px;">
                                    <!-- Div row chứa các thành phần -->
                                    <div class="row mb-3 border-bottom pb-3">
                                        <!-- Div row đầu tiên chứa text và button -->
                                        <div class="row">
                                            <div class="col-md-9">
                                                <h4 class="mb-0">Room</h4>
                                            </div>
                                            <div class="col-md-3 text-right" id="seeAllButton3">
                                                <button class="btn btn-primary ml-auto">See All</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Div row chứa 2 card -->
                                    <c:forEach items="${listRoom}" begin="0" end="1" var="r">
                                        <div class="row">
                                            <div class="col-md-6" style="margin-top: 10px;">
                                                <div class="card">
                                                    <div class="card-body">
                                                        <h5 class="card-title">${r.roomName}
                                                            <span class="icon" style="float: right;">
                                                            <i class="fa-solid fa-user"></i>
                                                        </span>
                                                        </h5>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <%--set--%>
                            <div class="tab-pane fade" id="set" role="tabpanel" aria-labelledby="profile-tab0">
                                <c:forEach items="${requestScope.listSet}" var="s">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="card" style="border: 1px solid #ccc;
                                            border-radius: 5px;
                                            padding: 10px;
                                            max-width: 300px;
                                            margin: 10px">
                                                <div class="card-content" style="display: flex;
                                                flex-direction: column;">
                                                    <h5 class="card-title" style="font-size: 18px;
                                                    margin-bottom: 10px;">${s.getSName()}</h5>
                                                    <div class="card-details" style="  display: flex;
                                                    align-items: center;">
                                                        <img src="${s.getUser().getAvatar()}" alt="Image"
                                                             class="circular-image"
                                                             style="  width: 30px;
                                                        height: 30px;
                                                        border-radius: 50%;
                                                        margin-right: 10px;"/>
                                                        <p class="card-text" style=" flex: 1;
                                                        margin-right: 10px;
                                                        margin-top: 10px;">${s.getUser().getUsername()}</p>
                                                        <button class="btn" style=" padding: 8px 10px;
                                                        background-color: #007bff;
                                                        color:white;">Xem trước
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <%--user--%>
                            <div class="tab-pane fade" id="user" role="tabpanel" aria-labelledby="contact-tab0">
                                <c:forEach items="${listUser}" var="u" varStatus="loop">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="card">
                                                <img src="${u.avatar}" class="card-img-top" alt="avatar"/>
                                                <div class="card-body">
                                                    <h5 class="card-title">${u.username}</h5>
                                                    <h5 class="card-subtitle">
                                                        <p class="d-inline"><i
                                                                class="fa-solid fa-folder-open"></i> ${countSet[loop.index]}
                                                            set
                                                        </p>
                                                        <p class="d-inline"><i
                                                                class="fa-solid fa-user"></i> ${countRoom[loop.index]}
                                                            room
                                                        </p>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <%--room--%>
                            <div class="tab-pane fade" id="room" role="tabpanel" aria-labelledby="room-tab0">
                                <c:forEach items="${listRoom}" var="r">
                                    <div class="row">
                                        <div class="col-md-6" style="margin-top: 10px;">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">${r.roomName}
                                                        <span class="icon" style="float: right;">
                                                            <i class="fa-solid fa-user"></i>
                                                        </span>
                                                    </h5>
                                                                                                           </h5>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script>
        function viewSet(sid) {
            window.location.href = '/Quizzicle/user/set/get?setID=' + sid;
        }
    </script>
</main>
<jsp:include page="../../../components/footer.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        // Kích hoạt tab Bootstrap
        $('[data-mdb-tab-init]').on('click', function () {
            var target = $(this).attr('data-mdb-target');
            $('.tab-pane').removeClass('show active');
            $(target).addClass('show active');
        });
    });
</script>
</body>
</html>
