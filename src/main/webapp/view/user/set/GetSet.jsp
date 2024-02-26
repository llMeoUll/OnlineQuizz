<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/ViewSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="">
        <img src="../.././imagines/logo1250x1250.png" alt="Quizzicle Logo" width="30" height="30" class="d-inline-block align-text-top me-2">
        Quizzicle

<div id="container">
    <h1 class="mt-5 mb-4">ViewSet - Flashcard Set</h1>
    <form action="./getset" method="post">
    <div id="setInfo">
        <h2>Set Information</h2>
        <p><strong>Set Name:</strong> <%= request.getAttribute("setName") %></p>
        <p><strong>Description:</strong> <%= request.getAttribute("setDescription") %></p>
        <p><strong>Hashtags:</strong> <%= request.getAttribute("hashtags") %></p>
    </div>

    <!-- Display list of questions -->
    <div id="questionList">
        <h2>Questions</h2>
        <ul>
            <li>
                <strong>Question 1:</strong> <%= request.getAttribute("question1") %><br>
                <strong>Answer:</strong> <%= request.getAttribute("answer1") %>
            </li>
            <li>
                <strong>Question 2:</strong> <%= request.getAttribute("question2") %><br>
                <strong>Answer:</strong> <%= request.getAttribute("answer2") %>
            </li>
            <div id="questionList">
                <h2>Questions</h2>
                <ul>
                    <%-- Retrieve list of questions from backend and display --%>
                    <% List<Question> questions = (List<Question>) request.getAttribute("questions"); %>
                    <% for (Question question : questions) { %>
                    <li>
                        <strong>Question:</strong> <%= question.getQuestionText() %><br>
                        <strong>Answer:</strong> <%= question.getAnswer() %>
                    </li>
                    <% } %>
                </ul>
            </div>
        </ul>
    </div>

</div>
</body>

