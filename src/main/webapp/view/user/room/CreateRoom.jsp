<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/28/2024
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create room</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"/>
<div class="container">
    <div class="modal fade" id="createRoomModal" tabindex="-1" aria-labelledby="createRoomModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="createRoomModalLabel">Create Room</h5>
                    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}">
                        <span aria-hidden="true">&times;</span>
                    </a>
                </div>
                <div class="modal-body">
                    <!-- Add your form elements for creating a room here -->
                    <form action="${pageContext.request.contextPath}/user/room/create" method="post">
                        <div class="form-group">
                            <label for="roomName" class="text-purple">Room Name</label>
                            <input type="text" class="form-control" id="roomName" name="roomName"
                                   placeholder="Enter room name" required>
                        </div>

                        <div class="form-group">
                            <label for="password" class="text-purple">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter password">
                        </div>

                        <div class="form-group">
                            <label for="description" class="text-purple">Description</label>
                            <textarea class="form-control" id="description" name="description"
                                      placeholder="Enter room description" rows="3"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Create Room</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/CreateRoom.js"></script>
</body>
</html>
