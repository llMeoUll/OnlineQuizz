<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/16/2024
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home page</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<!-- header -->
<jsp:include page="components/header.jsp"></jsp:include>
<!-- content -->
<main>
    <div id="carouselExampleIndicators" class="carousel slide" style="max-width: 100%;">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner" style="max-height: 100%;">
            <div class="carousel-item active">
                <img src="./images/carousel1.jpg" class="d-block w-100" style="width: 100%;height: auto;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="./images/carousel2.jpg" class="d-block w-100" style="width: 100%;height: auto;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="./images/carousel3.jpg" class="d-block w-100" style="width: 100%;height: auto;" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</main>
<!-- Footer -->
<jsp:include page="components/footer.jsp"></jsp:include>
</body>
</html>