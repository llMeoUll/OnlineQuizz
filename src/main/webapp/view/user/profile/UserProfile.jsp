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
</head>

<body>
<!-- header -->
<jsp:include page="../../../components/header.jsp"></jsp:include>
<!-- header -->
<main>
    <div class="SearchResultPage">
        <section id="SearchResultPageHeader-mainExperiment">
            <div class="SearchResultPageHeader-container">
                <div class="SearchResultPageHeader-queryFeedback">
                    <div class="SearchResultsPageHeader-resultsFor" id="SearchResultsPageHeader-resultsFor">
                        <img src="${sessionScope.user.avatar}"
                             alt="avatar"
                             style="height: 40px; width: 40px; border-radius: 50%; margin-right: 10px;"/><b
                            style="font-size: 30px; margin-top: 5%;">${sessionScope.user.email}</b>
                    </div>
                </div>
                <div class="SearchResultsPageHeader-subNavExperiment" id="SearchResultsPageHeader-subNavItem">
                    <div class="TabsWrapper">
                        <ul class="nav nav-tabs mb-3" id="myTab0" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button data-mdb-tab-init class="nav-link" id="profile-tab0" data-mdb-target="#set"
                                        type="button" role="tab" aria-controls="profile" aria-selected="false">
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
                        <%--set--%>
                        <div class="tab-pane fade" id="set" role="tabpanel" aria-labelledby="profile-tab0">
                            <c:forEach items="${requestScope.listS}" var="s">
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
                        <%--room--%>
                        <div class="tab-pane fade" id="room" role="tabpanel" aria-labelledby="room-tab0">
                            <c:forEach items="${requestScope.listR}" var="r">
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
                </div>
            </div>
        </section>
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
