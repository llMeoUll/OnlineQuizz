<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/10/2024
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        /**/
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

        .panel .panel-heading,
        .panel > :first-child {
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .panel-body {
            padding: 25px 20px;
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

    </style>
</head>
<body>
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
                                            <p id="likeCount_${c.getCommentId()}">0</p>
                                        </button>
                                        <button class="btn btn-sm btn-default dislike-btn" data-id="${c.getCommentId()}"
                                                onclick="clickDislike(${c.getCommentId()})">
                                            <i class="fa fa-thumbs-o-down"></i>
                                            <p id="dislikeCount_${c.getCommentId()}">0</p>
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
                                                        <a class="btn btn-sm btn-default btn-hover-success" href="#"><i
                                                                class="fa fa-thumbs-up"></i>${reply.likes} Likes</a>
                                                        <a class="btn btn-sm btn-default btn-hover-danger" href="#"><i
                                                                class="fa fa-thumbs-down"></i>${reply.unlikes}
                                                            disLikes</a>
                                                    </div>
                                                    <button class="btn btn-sm btn-default btn-hover-primary" href="#"
                                                            onclick="toggleForm('${reply.getCommentId()}')">
                                                        Comment
                                                    </button>
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
