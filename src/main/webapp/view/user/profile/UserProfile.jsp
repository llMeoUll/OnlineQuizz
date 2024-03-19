<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/25/2024
  Time: 3:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
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
    <link rel="stylesheet" href=".././css/HomePage.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .card-info p:hover {
            font-weight: bold;
            background-color: #f0f0f0; /* Adjust the color as needed */
        }

        .nav-link {
            color: black !important;
        }

        .nav-link:hover {
            color: rgb(40, 110, 231) !important;
        }


        main {
            margin-top: 25px;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            margin: -5px;
            /* Đảm bảo giữa các col có khoảng cách */
        }


        .card {
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .card img {
            margin-bottom: 10px;
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
<!-- header -->
<jsp:include page="../../../components/header.jsp"></jsp:include>
<!-- header -->
<main class="justify-content-center ">
    <div class="SearchResultPage mt-24" style=" max-width: calc(100% - 100px); padding: 30px 20px;">
        <section id="SearchResultPageHeader-mainExperiment">
            <div class="SearchResultPageHeader-container">
                <div class="SearchResultPageHeader-queryFeedback pt-4">
                    <div class="SearchResultsPageHeader-resultsFor mb-3 flex" style="margin-left: 130px;"
                         id="SearchResultsPageHeader-resultsFor">
                        <img src="${sessionScope.user.avatar}"
                             alt="avatar"
                             style="height: 40px; width: 40px; border-radius: 50%; margin-right: 10px;"/>
                        <b style="font-size: 30px;">${sessionScope.user.givenName}</b>
                    </div>
                </div>

                <div class="SearchResultsPageHeader-subNavExperiment" id="SearchResultsPageHeader-subNavItem">
                    <div class="TabsWrapper">
                        <ul class="nav nav-tabs mb-3" style="margin-left: 130px; margin-right: 130px" id="myTab0"
                            role="tablist">
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="profile-tab0" data-mdb-target="#set"
                                        type="button" role="tab" aria-controls="profile" aria-selected="true">
                                    Set
                                </button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="room-tab0" data-mdb-target="#room"
                                        type="button" role="tab" aria-controls="contact" aria-selected="false">
                                    Room
                                </button>
                            </li>
                        </ul>
                        <div class="tab-content" style="margin-left: 100px; margin-right: 130px;padding-bottom: 30px"
                             id="myTabContent0">
                            <%--set--%>
                            <div class="tab-pane fade show active" style="margin-left: 100px" id="set" role="tabpanel"
                                 aria-labelledby="profile-tab0">
                                <div class="row">
                                    <c:choose>
                                        <c:when test="${empty requestScope.listS}">
                                            <!-- Print an alternative HTML structure if listSet is empty -->
                                            <div class="col-md-12">
                                                <div>
                                                    <h2 class="font-weight-bold mb-4 text-center">No results.</h2>
                                                    <h4 class="font-weight-bold mb-4 text-center">Here are some
                                                        suggestions to improve your search
                                                        results:</h4>
                                                    <ul class="mb-0 pl-3"
                                                        style="display: flex; flex-direction: column; margin-left: 20vw;">
                                                        <li class="mb-2">Check your spelling or try a different
                                                            spelling
                                                        </li>
                                                        <li class="mb-2">Search with different keywords</li>
                                                        <li>Clear filter</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${requestScope.listS}" var="s">
                                                <div class="col-md-6">
                                                    <div class="card" style="border: 1px solid #ccc;
                                    border-radius: 5px;
                                    padding: 20px;
                                    max-width: 400px;
                                    min-width: 300px;
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
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <%--room--%>
                            <div class="tab-pane fade pb-4" id="room" role="tabpanel" aria-labelledby="room-tab0">
                                <div class="row">
                                    <c:choose>
                                        <c:when test="${empty listR}">
                                            <!-- Print an alternative HTML structure if listSet is empty -->
                                            <div class="col-md-12">
                                                <div>
                                                    <h2 class="font-weight-bold mb-4 text-center">No results.</h2>
                                                    <h4 class="font-weight-bold mb-4 text-center">Here are some
                                                        suggestions to improve your search
                                                        results:</h4>
                                                    <ul class="mb-0 pl-3"
                                                        style="display: flex; flex-direction: column; margin-left: 20vw;">
                                                        <li class="mb-2">Check your spelling or try a different
                                                            spelling
                                                        </li>
                                                        <li class="mb-2">Search with different keywords</li>
                                                        <li>Clear filter</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${listR}" var="r">
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
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
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
