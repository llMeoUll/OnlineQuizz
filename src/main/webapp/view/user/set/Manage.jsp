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


</head>
<body>
<header>

</header>
<%--header--%>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<main>
    <!-- ======= Breadcrumbs ======= -->
    <div class="container">
<<<<<<< HEAD
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb bg-transparent">
            </ol>
        </nav>
        <h2 class="text-3xl font-bold text-center">All SET</h2>
    </div><!-- End Breadcrumbs -->
    <div id="create-set-content" class="mt-10">
        <%--        <div class="container">--%>
        <%--            <div class="row">--%>
        <%--                <div class="col-md-12">--%>
        <%--                    <c:forEach var="c" items="${listSet}">--%>
        <%--                        <div class="card mb-3">--%>
        <%--                            <div class="card-body mt-1">--%>
        <%--                                <h5 class=""><a href="./set/get?setID=${c.getSId()}">${c.getSName()}</a></h5>--%>
        <%--                            </div>--%>
        <%--                        </div>--%>
        <%--                    </c:forEach>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <section class="vh-100">
            <div class="container py-5 h-52">
                <div class="row d-flex justify-content-center align-items-center h-52">
                    <div class="col col-lg-9 col-xl-7">
                        <div class="card rounded-3">
                            <div class="card-body p-4">
                                <table class="table mb-4">
                                    <thead>
                                    <tr>
                                        <th scope="col">No.</th>
                                        <th scope="col">Name of set</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listSet}" var="c" varStatus="loop">
                                        <tr>
                                            <th scope="row">${loop.index + 1}</th>
                                            <td>${c.getSName()}</td>
                                            <td>
                                                <a href="./set/get?setID=${c.getSId()}" class="btn btn-success ms-1">View</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>
=======
        <div class="row">
            <div class="col-md-12">
                <c:forEach var="c" items="${listSet}">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title"><a href="./set/get?setId=${c.getSId()}">${c.getSName()}</a></h5>
>>>>>>> 894b06f202f21815805085fe4c213da107af7a3c
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main><!-- End #main -->

<jsp:include page="../../../components/footer.jsp"></jsp:include>
<%--<script src="../.././js/GetSet.js"></script>--%>
</body>
</html>