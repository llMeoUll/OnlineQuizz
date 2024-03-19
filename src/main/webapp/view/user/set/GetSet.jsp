<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);--%>
<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
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
    <%-- comment   --%>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="../../css/GetSet.css">
    <link rel="stylesheet" href="../../css/HomePage.css">
</head>
<body class="bg-white">
</div>
<%--header--%>
<jsp:include page="../../../components/header.jsp"></jsp:include>

<%--main--%>
<main class="bg-white">
    <%--    nam--%>
    <div class=" z-2" style="background-color: #fff">

        <hr class="container">
        <%--        flashcard--%>
        <div class="flashcard-fl mt-32">
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
            <a class="btn-success" href="#" onclick="confirmDelete()">Delete</a>
            <div style="justify-content: center;">
                <!--  Update -->
                <a class="btn btn-primary" href="/Quizzicle/user/set/update">Update</a>
                <form id="myForm" action="./get" method="post">
                    <div id="ratingStars" class="" style="position: static">
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
            <table class="table mb-4">
                <thead>
                <tr>
                    <th scope="col">Question</th>
                    <th scope="col">Answer</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="a" items="${listQuestion}">
                    <tr>
                        <th>
                            <pre style="white-space: pre-line">${a.getQuestion()}</pre>
                        </th>
                        <th>
                            <pre style="white-space: pre-line">${a.getAnswer()}</pre>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

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
                                    <form id="commentId=${c.getCommentId()}" action="../comment/add" method="post"
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
                                                            <a class="btn btn-sm btn-default btn-hover-success"
                                                               href="#"><i
                                                                    class="fa fa-thumbs-up"></i>${reply.likes} </a>
                                                            <a class="btn btn-sm btn-default btn-hover-danger" href="#"><i
                                                                    class="fa fa-thumbs-down"></i>${reply.unlikes}
                                                                </a>
                                                        </div>
<%--                                                        <button class="btn btn-sm btn-default btn-hover-primary"--%>
<%--                                                                href="#"--%>
<%--                                                                onclick="toggleForm('${reply.getCommentId()}')">--%>
<%--                                                            Comment--%>
<%--                                                        </button>--%>
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
</main>
<%--footer--%>
<jsp:include page="../../../components/footer.jsp"></jsp:include>
<script src="../.././js/GetSet.js"></script>
<script src="${requestScope.getContextPath}/Quizzicle/js/Comment.js"></script>
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
</body>
</html>