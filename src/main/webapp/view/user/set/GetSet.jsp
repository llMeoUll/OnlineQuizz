<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);--%>
<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../.../css/GetSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
<%--    comment--%>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <style>
        .fl {
            width: 100%;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f1f1f1;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e9e9e9;
        }

        td {
            word-wrap: break-word;
            overflow-wrap: break-word;
        }
      /**/
        /*#create-set-content {*/
        /*    position: fixed;*/
        /*    top: 0;*/
        /*    width: 100%;*/
        /*    height: 64px;*/
        /*    background-color: #0d6efd;*/
        /*    z-index: 2;*/
        /*}*/

        .bootdey {
            padding-top: 64px; /* Đảm bảo không bị đè lên phần tử trên */
            margin-top: 500px;
        }
        body {
            margin-top: 20px;
            background: #ebeef0;
        }

        .img-sm {
            width: 46px;
            height: 46px;
        }

        .panel {
            box-shadow: 0 2px 0 rgba(0, 0, 0, 0.075);
            border-radius: 0;
            border: 0;
            margin-bottom: 15px;
        }

        .panel .panel-footer,
        .panel > :last-child {
            border-bottom-left-radius: 0;
            border-bottom-right-radius: 0;
        }
      .fl {
                    width: 100%;
                    overflow-x: auto;
                }
        .media-block .media-left {
            display: block;
            float: left
        }

        .media-block .media-right {
            float: right
        }

        .media-block .media-body {
            display: block;
            overflow: hidden;
            width: auto
        }

        .middle .media-left,
        .middle .media-right,
        .middle .media-body {
            vertical-align: middle
        }

        .thumbnail {
            border-radius: 0;
            border-color: #e9e9e9
        }

        .tag.tag-sm,
        .btn-group-sm > .tag {
            padding: 5px 10px;
        }

        .tag:not(.label) {
            background-color: #fff;
            padding: 6px 12px;
            border-radius: 2px;
            border: 1px solid #cdd6e1;
            font-size: 12px;
            line-height: 1.42857;
            vertical-align: middle;
            -webkit-transition: all .15s;
            transition: all .15s;
        }

        .text-muted,
        a.text-muted:hover,
        a.text-muted:focus {
            color: #acacac;
        }

        .text-sm {
            font-size: 0.9em;
        }

        .text-5x,
        .text-4x,
        .text-5x,
        .text-2x,
        .text-lg,
        .text-sm,
        .text-xs {
            line-height: 1.25;
        }

        .btn-trans {
            background-color: transparent;
            border-color: transparent;
            color: #929292;
        }

        .btn-icon {
            padding-left: 9px;
            padding-right: 9px;
        }

        .btn-sm,
        .btn-group-sm > .btn,
        .btn-icon.btn-sm {
            padding: 5px 10px !important;
        }

        .mar-top {
            margin-top: 15px;
        }
      
      .panel .panel-heading,
        .panel > :first-child {
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .panel-body {
            padding: 25px 20px;
        }
    </style>
    <script>
<<<<<<< HEAD
=======
        function confirmDelete() {
            var confirmation = window.confirm("Are you sure you want to delete this set ${setID}?");
            if (confirmation) {
                window.location.href = "../set/delete?set-id=${setID}";
            } else {
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            var flashcards = document.getElementsByClassName('flashcard');
            var currentIndex = 0;
>>>>>>> 9e90cad31ab26f594afccd5bc36c842bcb0bf491

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

<<<<<<< HEAD
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

        <h5 class="container text-success">All Terminology:</h5>
        <div class="container">
            <!-- Button trigger modal -->
            <a class="btn-success" href="#" onclick="confirmDelete()">Delete</a>
            <div style="justify-content: center;">
                <!--  Update -->
                <a class="btn btn-primary" href="/Quizzicle/user/set/update">Update</a>
            </div>
            <script>
                function confirmDelete() {
                    var confirmation = window.confirm("Are you sure you want to delete this set ${setID}?");
                    if (confirmation) {
                        window.location.href = "../set/delete?set-id=${setID}";
                    } else {
                    }
                }
            </script>


            <table>
                <thead>
                <tr>
                    <th>Question</th>
                    <th>Answer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${listQuestion}">
                    <tr>
                        <td>
                            <pre style="white-space: pre-line">${a.getQuestion()}</pre>
                        </td>
                        <td>
                            <pre style="white-space: pre-line">${a.getAnswer()}</pre>
                        </td>
                    </tr>
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
=======
                <button type="submit">Rate</button>
            </form>
            <script>
                function submitForm() {
                    document.getElementById("myForm").submit();
                }
            </script>
            <style>
              

    </style>
</head>
<body>
</div>
<%--header--%>
<%--<div class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">--%>
<%--    <main id="main">--%>

<%--        &lt;%&ndash;        <h1 class="container h2" style="padding-top: 100px;">Flashcards: ${fl.getTitle()}</h1>&ndash;%&gt;--%>
<%--        <nav class="navbar navbar-expand-lg navbar-dark bg-primary container">--%>
<%--            <div class="container-fluid">--%>
<%--                <a class="navbar-brand" href="#">Quizzicle</a>--%>
<%--                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"--%>
<%--                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                    <span class="navbar-toggler-icon"></span>--%>
<%--                </button>--%>
<%--                <div class="collapse navbar-collapse" id="navbarNav">--%>
<%--                    <ul class="navbar-nav me-auto">--%>
<%--                        <!-- Add any additional navbar links here -->--%>
<%--                    </ul>--%>
<%--                    <form class="d-flex">--%>
<%--                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">--%>
<%--                        <button class="btn btn-outline-light" type="submit">Search</button>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </nav>--%>

<%--        <hr class="container">--%>
<%--        <div class="flashcard-fl">--%>
<%--            <c:forEach var="i" items="${listQuestion}" varStatus="status">--%>
<%--                <div class="flashcard ${status.index > 0 ? 'hidden' : ''}">--%>
<%--                    <div class="question">--%>
<%--                        <h2>${i.getQuestion()}</h2>--%>
<%--                    </div>--%>
<%--                    <div class="answer">--%>
<%--                        <p>${i.getAnswer()}</p>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>

<%--        <div style="justify-content: center;">--%>
<%--            <div class="controls">--%>
<%--                <button id="prevBtn">Prev</button>--%>
<%--                <button id="nextBtn">Next</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <hr class="container">--%>


<%--        <h5 class="container text-success">All Terminology:</h5>--%>
<%--        <div class="container">--%>
<%--            <!-- Button trigger modal -->--%>
<%--            <a class="btn-success" href="#" onclick="confirmDelete()">Delete</a>--%>
<%--            <div style="justify-content: center;">--%>
<%--                <!--  Update -->--%>
<%--                <a class="btn btn-primary" href="/Quizzicle/user/set/update">Update</a>--%>
<%--            </div>--%>
<%--            <table>--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>Question</th>--%>
<%--                    <th>Answer</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="a" items="${listQuestion}">--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <pre style="white-space: pre-line">${a.getQuestion()}</pre>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <pre style="white-space: pre-line">${a.getAnswer()}</pre>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>


<%--            </table>--%>
<%--            <form id="myForm" action="./get" method="post">--%>
<%--                <div id="ratingStars" class="rating">--%>
<%--                    <input type="radio" id="star5" name="numberOfStar" value="5" onclick="submitForm()">--%>
<%--                    <label for="star5" title="5 stars"></label>--%>
<%--                    <input type="radio" id="star4" name="numberOfStar" value="4" onclick="submitForm()">--%>
<%--                    <label for="star4" title="4 stars"></label>--%>
<%--                    <input type="radio" id="star3" name="numberOfStar" value="3" onclick="submitForm()">--%>
<%--                    <label for="star3" title="3 stars"></label>--%>
<%--                    <input type="radio" id="star2" name="numberOfStar" value="2" onclick="submitForm()">--%>
<%--                    <label for="star2" title="2 stars"></label>--%>
<%--                    <input type="radio" id="star1" name="numberOfStar" value="1" onclick="submitForm()">--%>
<%--                    <label for="star1" title="1 star"></label>--%>

<%--                    <input type="hidden" name="setId" value="${requestScope.setID}">--%>
<%--                </div>--%>
<%--                <button type="submit">Rate</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </main>--%>
<%--</div>--%>
<%--&lt;%&ndash;content&ndash;%&gt;--%>
<%--<div id="create-set-content" style="margin-top: 64px">--%>
<%--</div>--%>

<%--Comment--%>
<div class="container bootdey">
    <div class="col-md-12 bootstrap snippets">
        <!-- panel 1 -->
        <div class="panel">
            <div class="panel-body" id="panel1">
                <form action="./add" method="post">
                    <textarea name="comment" class="form-control" rows="2"
                              placeholder="What are you thinking?"></textarea>
                    <div class="mar-top clearfix">
                        <input type="hidden" name="setID" value="${sessionScope.setID}">
                        <button class="btn btn-sm btn-primary pull-right" type="submit"><i
                                class="fa fa-pencil fa-fw"></i> Share
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- panel 2 -->
        <div class="panel">
            <div class="panel-body" id="panel2">
                <div class="media-block pad-all">
                    <c:forEach items="${listC}" var="c" varStatus="loop">
                        <div class="media-block">
                            <a class="media-left" href="#"><img class="img-circle img-sm" alt="Profile Picture"
                                                                src="${c.getUser().getAvatar()}"></a>
                            <div class="media-body">
                                <div class="mar-btm">
                                    <a href="#"
                                       class="btn-link text-sm bold media-heading box-inline">${c.getUser().getGivenName()}</a>
                                    <p class="text-muted text-sm"><i class="fa fa-mobile fa-lg"></i><fmt:formatDate
                                            value="${c.getCreatedAt()}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </div>
                                <p>${c.content}</p>
                                    <%--x`like/unlike--%>
                                <div class="pad-ver">
                                    <div class="btn-group">
                                        <button class="btn btn-sm btn-default like-btn" data-id="${c.getCommentId()}"
                                                onclick="clickLike(${c.getCommentId()})">
                                            <i class="mr-3 fa fa-thumbs-o-up"></i>
                                            <p id="likeCount_${c.getCommentId()}">${c.getLikes()}</p>
                                        </button>
                                        <button class="btn btn-sm btn-default dislike-btn" data-id="${c.getCommentId()}"
                                                onclick="clickDislike(${c.getCommentId()})">
                                            <i class="fa fa-thumbs-o-down"></i>
                                            <p id="dislikeCount_${c.getCommentId()}">${c.getUnlikes()}</p>
                                        </button>
                                    </div>
                                    <button class="btn btn-sm btn-default btn-hover-primary" href="#"
                                            onclick="toggleForm('${c.getCommentId()}')">
                                        Comment
                                    </button>
                                </div>

                                <form id="commentId=${c.getCommentId()}" action="./add" method="post"
                                      style="display: none;">
                                    <textarea name="comment" class="form-control" rows="2"
                                              placeholder="What are you thinking?"></textarea>
                                    <input type="hidden" value="${c.getCommentId()}" name="replyId">
                                    <div class="mar-top clearfix">
                                        <input type="hidden" name="setID" value="${sessionScope.setID}">
                                        <button class="btn btn-sm btn-primary pull-right" type="submit"><i
                                                class="fa fa-pencil fa-fw"></i> Share
                                        </button>
                                    </div>
                                </form>
                                <hr>
                                    <%-- reply comment--%>
                                <div>
                                    <c:forEach items="${replyList[loop.index]}" var="reply">
                                        <div class="media-block pad-all">
                                            <a class="media-left" href="#"><img class="img-circle img-sm"
                                                                                alt="Profile Picture"
                                                                                src="${reply.getUser().getAvatar()}"></a>
                                            <div class="media-body">
                                                <div class="mar-btm">
                                                    <a href="#"
                                                       class="btn-link text-semibold media-heading box-inline">${reply.getUser().getGivenName()}</a>
                                                    <p class="text-muted text-sm"><i class="fa fa-globe fa-lg"></i>
                                                        <fmt:formatDate value="${c.getCreatedAt()}"
                                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    </p>
                                                </div>
                                                <p>${reply.getContent()}</p>
                                                <div>
                                                    <div class="btn-group">
                                                        <button class="btn btn-sm btn-default like-btn" data-id="${reply.getCommentId()}"
                                                                onclick="clickLike(${reply.getCommentId()})">
                                                            <i class="mr-3 fa fa-thumbs-o-up"></i>
                                                            <p id="likeCount_${reply.getCommentId()}">${reply.getLikes()}</p>
                                                        </button>
                                                        <button class="btn btn-sm btn-default dislike-btn" data-id="${c.getCommentId()}"
                                                                onclick="clickDislike(${reply.getCommentId()})">
                                                            <i class="fa fa-thumbs-o-down"></i>
                                                            <p id="dislikeCount_${reply.getCommentId()}">${reply.getUnlikes()}</p>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%--script for comment--%>
<%--nam_set--%>
<script>
    function submitForm() {
        document.getElementById("myForm").submit();
    }
</script>
<script>
    function toggleForm(replyId) {
        var divContainsForm = document.getElementById('commentId=' + replyId);
        if (divContainsForm.style.display === 'none' || form.style.display === '') {
            divContainsForm.style.display = 'block';
        } else {
            divContainsForm.style.display = 'none';
        }
        console.log('clicked');
    }
>>>>>>> 9e90cad31ab26f594afccd5bc36c842bcb0bf491

    // Function to get initial likes count from the server
    function getInitialLikes(commentId) {
        // Perform AJAX request to fetch initial likes count
        fetch("${requestScope.getContextPath}/Quizzicle/user/comment/add")
            .then(response => response.json())
            .then(data => {
                document.getElementById(`likeCount_` + commentId).innerText = data.likes;
            })
            .catch(error => console.error('Error:', error));
    }

    // Function to get initial unlikes count from the server
    function getInitialUnlikes(commentId) {
        // Perform AJAX request to fetch initial unlikes count
        fetch(`add`)
            .then(response => response.json())
            .then(data => {
                document.getElementById(`dislikeCount_` + commentId).innerText = data.unlikes;
            })
            .catch(error => console.error('Error:', error));
    }

    // Function to handle like click
    function clickLike(commentId) {
        let likeCount = document.getElementById(`likeCount_` + commentId);
        likeCount.innerText = parseInt(likeCount.innerText) + 1;
        updateLikesAndUnlikes(commentId);
    }

    // Function to handle dislike click
    function clickDislike(commentId) {
        let dislikeCount = document.getElementById(`dislikeCount_` + commentId);
        dislikeCount.innerText = parseInt(dislikeCount.innerText) + 1;
        updateLikesAndUnlikes(commentId);
    }

    // Function to update likes and unlikes counts on the server
    function updateLikesAndUnlikes(commentId) {
        let likeCount = document.getElementById(`likeCount_` + commentId).innerText;
        let dislikeCount = document.getElementById(`dislikeCount_` + commentId).innerText;

        // Perform AJAX request to update server-side like and dislike counts
        $.ajax({
            url: './add',
            type: 'POST',
            data: JSON.stringify({
                likes: likeCount,
                unlikes: dislikeCount,
                comment_id: commentId
            }),
            success: function (data) {
                console.log(data);
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    }

    // Periodically update likes and dislikes eve   ry 5 seconds
    // setInterval(function () {
    //     // You may call the updateLikesAndUnlikes function here if you want to update periodically
    // }, 5000);

    // Fetch initial likes and unlikes counts when the page loads
    document.addEventListener("DOMContentLoaded", function () {
        getInitialLikes(${c.getCommentId()});
        getInitialUnlikes(${c.getCommentId()});
    });

</script>


<script src="../.././js/GetSet.js"></script>
</body>

</html>