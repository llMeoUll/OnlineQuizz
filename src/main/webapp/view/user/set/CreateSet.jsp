<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 24/01/2024
  Time: 8:37 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create a new set</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/CreateSet.css">
    <link rel="stylesheet" href="webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<div class="container">
    <h1 class="mt-5 mb-4">CREATE A NEW SET</h1>
    <form action="./create" method="post">
        <div class="name-container">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
        </div>
        <div class="description-container">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" cols="50"></textarea>
        </div>
        <div class="hashtag-container">
            <label for="hashtag">Hashtag:</label>
            <input type="text" id="hashtag" name="hashtag">
        </div>
        <div class="privacy-container">
            <label for="privacy">Privacy:</label>
            <select id="privacy" name="privacy">
                <option value="public">Public</option>
                <option value="private">Private</option>
            </select>
        </div>
        <input name="number-of-question" placeholder="Enter number of question"/>
        <button>
            
        </button>
        <div class="container" name="list-questions"  >

        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<!-- Thêm JavaScript để xử lý tìm kiếm -->
<script src="./js/CreateSet.js"></script>
</body>
</html>
