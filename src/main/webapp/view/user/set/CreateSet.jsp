<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 2/20/2024
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Set | Quizzicle</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/Login.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1 class="d-inline">Create a new set</h1>
        <button type="button" class="btn btn-primary">Create</button>
    </div>
    <form action="./create" method="post">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title"/>
            <label for="title">Title</label>
        </div>
        <div class="form-floating mb-3">
            <textarea type="text" class="form-control" id="description" name="description" placeholder="Add a description"
                      style="height: 100px"></textarea>
            <label for="description">Description</label>
        </div>
        <div class="form-floating mb-3" id="hashtag-input">
            <input type="text" class="form-control" id="hashtag-text" name="hashtag" placeholder="Enter hashtag (comma-separated)"/>
            <label for="hashtag-text">Hashtags</label>
            <div class="mt-4" id="hashtag-container"></div>
        </div>
<%--        <div class="form-control" id="hashtag-input">--%>
<%--            <input type="text" class="form-control" id="hashtag-text" placeholder="Type hashtag...">--%>
<%--            <div id="hashtag-container"></div>--%>
<%--        </div>--%>

        <div class="form-floating mb-3">
            <select class="form-select" id="privacy">
                <option value="public">Public</option>
                <option value="private">Private</option>
            </select>
            <label for="privacy">Privacy</label>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <div class="d-flex align-items-center justify-content-between mb-1 mx-2">
                    <div>
                        1
                    </div>
                    <div>
                        <span>
                        <i class="fa-solid fa-trash"></i>
                    </span>
                    </div>
                </div>
                <div class="form-floating">
                    <select class="form-select" id="type" onchange="handleSelectType();">
                        <option value="multiple">Multiple choice</option>
                        <option value="true/false">True/False</option>
                        <option value="essay">Essay</option>
                    </select>
                    <label for="type">Type</label>
                </div>
            </div>
            <div class="card-body" id="multiple-choice">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="question" placeholder="Enter a question"/>
                    <label for="question">Question</label>
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="option1" placeholder="Option 1"/>
                    <i class="fa-solid fa-xmark"></i>
                </div>
                <div class="text-center">
                    <span onclick="handleAddOpt();"><i class="fa-solid fa-circle-plus"></i></span>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary mt-1">Create</button>
    </form>
</div>
<script src="../.././js/CreateSet.js"></script>
</body>
</html>
