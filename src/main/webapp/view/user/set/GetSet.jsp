<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/GetSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>

    <script>

    </script>

</head>
<body>
<%--</div>--%>
<%--header--%>
<div>
    <div class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">
        <main id="main" class="container">

            <%--        <h1 class="container h2" style="padding-top: 100px;">Flashcards: ${fl.getTitle()}</h1>--%>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary container">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Quizzicle</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav me-auto">
                            <!-- Add any additional navbar links here -->
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-light" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </main>
    </div>
    <div>

            <hr class="container">
            <div class="flashcard-fl">
                <c:forEach var="i" items="${listQuestion}" varStatus="status">
                    <div class="flashcard ${status.index > 0 ? 'hidden' : ''}">
                        <div class="question">
                            <h2>${i.getQuestion()}</h2>
                        </div>
                        <div class="answer">
                            <p>${i.getAnswer()}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div style="justify-content: center;">
                <div class="controls">
                    <button id="prevBtn">Prev</button>
                    <button id="nextBtn">Next</button>
                </div>
            </div>
            <hr class="container">

            <h5 class="container text-success">All Terminology:</h5>
            <div class="container">
                <!-- Button trigger modal -->
                <a class="btn-success" href="#" onclick="confirmDelete(${setID})">Delete</a>
                <div style="justify-content: center;">
                    <!--  Update -->
                    <a class="btn btn-primary" href="../.././update">Update</a>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th>Question</th>
                        <th>Answer</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${requestScope.listQuestion}">

                        <tr>
                            <td>
                                <pre style="white-space: pre-line">${a.getQuestion()}</pre>
                            </td>
                            <td>
                                <pre style="white-space: pre-line">${a.getAnswer()}</pre>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <form id="myForm" action="./get" method="post">
                    <div id="ratingStars" class="rating">
                        <input type="radio" id="star5" name="numberOfStar" value="5" onclick="submitForm()">
                        <label for="star5" title="5 stars"></label>
                        <input type="radio" id="star4" name="numberOfStar" value="4" onclick="submitForm()">
                        <label for="star4" title="4 stars"></label>
                        <input type="radio" id="star3" name="numberOfStar" value="3" onclick="submitForm()">
                        <label for="star3" title="3 stars"></label>
                        <input type="radio" id="star2" name="numberOfStar" value="2" onclick="submitForm()">
                        <label for="star2" title="2 stars"></label>
                        <input type="radio" id="star1" name="numberOfStar" value="1" onclick="submitForm()">
                        <label for="star1" title="1 star"></label>

                        <input type="hidden" name="setId" value="${requestScope.setID}">
                    </div>
                </form>

            </div>
    </div>


    </div>
    <%--content--%>
    <div id="create-set-content" style="margin-top: 64px">

</div>


<script src="../.././js/GetSet.js"></script>
</body>
</html>

