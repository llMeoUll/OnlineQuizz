<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/ViewSet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<%--header--%>
<%--<header class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">--%>
<%--    <main id="main" data-aos="fade-in">--%>
        <!-- ======= Breadcrumbs ======= -->
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-transparent">
                </ol>
            </nav>
            <h2>All SET</h2>
        </div><!-- End Breadcrumbs -->
    </main><!-- End #main -->
</header>

<%--content--%>
<div id="create-set-content" style="margin-top: 128px;">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:forEach var="c" items="${listSet}">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title"><a href="./set/get?setID=${c.getSId()}">${c.getSName()}</a></h5>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<%--<script src="../.././js/GetSet.js"></script>--%>
</body>
</html>