<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@import url(//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css);--%>
<html>
<head>
    <title>Get set</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/GetSet.css">
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
                <a class="btn btn-primary" href="/Quizzicle/user/set/update?setId=${requestScope.setId}">Update</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/set/self-test-setting?setId=${requestScope.setId}">Self test</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/set/self-test/history?setId=${requestScope.setId}">Self test history</a>

                <form id="myForm" action="./get" method="post">
                    <div id="ratingStars">
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
                        <input type="hidden" name="setId" value="${requestScope.setId}">
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

    <%--Write comment--%>
    <div class="container">
        <form action="${pageContext.request.contextPath}/user/comment/create" method="post">
                        <textarea name="comment" class="form-control mb-3" rows="2"
                                  placeholder="What are you thinking?"></textarea>
            <input type="hidden" name="setId" value="${requestScope.setId}">
            <button class="btn btn-primary" type="submit">
                <i class="fa fa-pencil fa-fw"></i> Send
            </button>
        </form>
    </div>
    <%--List comment--%>
    <div class="container">
        <c:forEach items="${listC}" var="c" varStatus="loop">
            <div class="card mb-3">
                <div class="card-header">
                    <div class="d-flex flex-start align-items-center">
                        <c:set var="noAvatar" value="${pageContext.request.contextPath}/images/noImage.png"/>
                        <img class="rounded-circle me-3"
                             src="${c.getUser().getAvatar() != null ? c.getUser().getAvatar() : noAvatar}" alt="avatar"
                             width="60"
                             height="60"/>
                        <div>
                            <h6 class="fw-bold text-primary mb-1">${c.getUser().getGivenName()} ${c.getUser().getFamilyName()}</h6>
                            <p class="text-muted small mb-0">
                                <fmt:formatDate value="${c.getCreatedAt()}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <p>${c.content}</p>
                </div>
                <div class="card-footer">
                    <div class="btn-group">
                        <button class="btn"
                                data-id="${c.getCommentId()}"
                                onclick="updateLike(${c.getCommentId()})">
                            <i class="fa-regular fa-thumbs-up"></i>
                            <span id="likeCount_${c.getCommentId()}">${c.getLikes()}</span>
                        </button>
                        <button class="btn btn-sm btn-default dislike-btn"
                                data-id="${c.getCommentId()}"
                                onclick="updateDislike(${c.getCommentId()})">
                            <i class="fa-regular fa-thumbs-down"></i>
                            <span id="dislikeCount_${c.getCommentId()}">${c.getUnlikes()}</span>
                        </button>
                    </div>
                    <button class="btn btn-sm btn-default btn-hover-primary" href="#"
                            onclick="toggleForm('${c.getCommentId()}')">
                        Comment
                    </button>
                    <form id="commentId=${c.getCommentId()}" action="../comment/create" method="post"
                          style="display: none;">
                                    <textarea name="comment" class="form-control" rows="2"
                                              placeholder="What are you thinking?"></textarea>
                        <input type="hidden" value="${c.getCommentId()}" name="replyId">
                        <div class="mt-3">
                            <input type="hidden" name="setId" value="${requestScope.setId}">
                            <button class="btn btn-primary" type="submit"><i
                                    class="fa fa-pencil fa-fw"></i> send
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <%-- reply comment--%>
            <c:forEach items="${replyList[loop.index]}" var="reply">
                <div class="card ms-5 mb-3">
                    <div class="card-header">
                        <div class="d-flex flex-start align-items-center">
                            <img class="rounded-circle me-3"
                                 src="${reply.getUser().getAvatar() != null ? reply.getUser().getAvatar() : noAvatar}"
                                 alt="avatar"
                                 width="60"
                                 height="60"/>
                            <div>
                                <h6 class="fw-bold text-primary mb-1">${reply.getUser().getGivenName()} ${reply.getUser().getFamilyName()}</h6>
                                <p class="text-muted small mb-0">
                                    <fmt:formatDate value="${reply.getCreatedAt()}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <p>${reply.content}</p>
                    </div>
                    <div class="card-footer">
                        <div class="btn-group">
                            <button class="btn"
                                    data-id="${reply.getCommentId()}"
                                    onclick="updateLike(${reply.getCommentId()})">
                                <i class="fa-regular fa-thumbs-up"></i>
                                <span id="likeCount_${reply.getCommentId()}">${reply.getLikes()}</span>
                            </button>
                            <button class="btn btn-sm btn-default dislike-btn"
                                    data-id="${reply.getCommentId()}"
                                    onclick="updateDislike(${reply.getCommentId()})">
                                <i class="fa-regular fa-thumbs-down"></i>
                                <span id="dislikeCount_${reply.getCommentId()}">${reply.getUnlikes()}</span>
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
    </div>
</main>
<%--footer--%>
<jsp:include page="../../../components/footer.jsp"></jsp:include>
<script>


    // Function to update likes and unlikes counts on the server
    function updateLike(commentId) {
        // Perform AJAX request to update server-side like and dislike counts
        $.ajax({
            url: '${pageContext.request.contextPath}/user/comment/update-like',
            type: 'POST',
            data: JSON.stringify({
                comment_id: commentId,
                set_id: ${requestScope.setId}
            }),
            success: function (data) {
                //get json data
                const dataJson = JSON.parse(data);
                // get like count
                let like = dataJson.like;
                // get old like count
                let oldLikeCount = parseInt($('#likeCount_' + commentId).text());
                // update like count
                $('#likeCount_' + commentId).text(oldLikeCount + like);
            }
        });
    }

    function updateDislike(commentId) {
        // Perform AJAX request to update server-side like and dislike counts
        $.ajax({
            url: '${pageContext.request.contextPath}/user/comment/update-dislike',
            type: 'POST',
            data: JSON.stringify({
                comment_id: commentId,
                set_id: ${requestScope.setId}
            }),
            success: function (data) {
                //get json data
                const dataJson = JSON.parse(data);
                console.log(data)
                // get like count
                let dislike = dataJson.dislike;
                // get old like count
                let oldDislikeCount = parseInt($('#dislikeCount_' + commentId).text());
                // update like count
                $('#dislikeCount_' + commentId).text(oldDislikeCount + dislike);
            }
        });
    }

    // auto update like and dislike comment


</script>
<script src="${pageContext. request. contextPath}/js/GetSet.js"></script>
</body>
</html>