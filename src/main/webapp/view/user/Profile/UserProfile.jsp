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
    <link rel="stylesheet" href="./css/HomePage.css">
</head>

<body>
<!-- header -->
<jsp:include page="../../../components/header.jsp"></jsp:include>
<!-- header -->

<!-- content -->
<main>
    <ul class="nav nav-tabs mb-3" id="myTab0" role="tablist">
        <li class="nav-item" role="presentation">
            <button
                    data-mdb-tab-init
                    class="nav-link active"
                    id="home-tab0"
                    data-mdb-target="#home0"
                    type="button"
                    role="tab"
                    aria-controls="home"
                    aria-selected="true"
            >
                Home
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button
                    data-mdb-tab-init
                    class="nav-link"
                    id="profile-tab0"
                    data-mdb-target="#profile0"
                    type="button"
                    role="tab"
                    aria-controls="profile"
                    aria-selected="false"
            >
                Profile
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button
                    data-mdb-tab-init
                    class="nav-link"
                    id="contact-tab0"
                    data-mdb-target="#contact0"
                    type="button"
                    role="tab"
                    aria-controls="contact"
                    aria-selected="false"
            >
                Contact
            </button>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent0">
        <div
                class="tab-pane fade show active"
                id="home0"
                role="tabpanel"
                aria-labelledby="home-tab0"
        >
            Tab 1 content.
        </div>
        <div class="tab-pane fade" id="profile0" role="tabpanel" aria-labelledby="profile-tab0">
            Tab 2 content
        </div>
        <div class="tab-pane fade" id="contact0" role="tabpanel" aria-labelledby="contact-tab0">
            Tab 3 content
        </div>
    </div>
</main>
<!-- content -->

<!-- Footer -->
<jsp:include page="../../../components/footer.jsp"></jsp:include>
<!-- Footer -->
</body>
<script src="./js/Search.js"></script>
</html>