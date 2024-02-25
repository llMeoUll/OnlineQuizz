<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/23/2024
  Time: 1:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SearchPage</title>
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
    <%--    <link rel="stylesheet" href="./css/HomePage.css">--%>
    <link rel="stylesheet" href="./css/SearchPage.css">
</head>
<body>
<%--<jsp:include page="../../../components/header.jsp"></jsp:include>--%>
<!-- header -->

<div class="nav-item mr-5 mt-1">
    <form action="search" method="post" class="form-inline my-2 my-lg-0"
          style="margin-top: 0.25rem; width: 120%">
        <div class="input-group input-group-sm" style="width: 20vw">
            <input name="txt" type="text" class="form-control" aria-label="Small"
                   aria-describedby="inputGroup-sizing-sm" placeholder="Search..." value="${txtSearch}"
                   style="font-size: 18px">
            <div class="input-group-append">
                <button type="submit" class="btn btn-secondary btn-number">
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </div>
    </form>
</div>
<%--<script>--%>
<%--    const availablekeywords = [--%>
<%--        "Biology",--%>
<%--        "Chemistry",--%>
<%--        "Physics",--%>
<%--        "Mathematics",--%>
<%--        "History",--%>
<%--        "Geography",--%>
<%--        "Literature",--%>
<%--        "Vocabulary",--%>
<%--        "Grammar",--%>
<%--        "Spanish",--%>
<%--        "French",--%>
<%--        "German",--%>
<%--        "Mandarin",--%>
<%--        "Japanese",--%>
<%--        "Economics",--%>
<%--        "Psychology",--%>
<%--        "Sociology",--%>
<%--        "Philosophy",--%>
<%--        "Art",--%>
<%--        "Music",--%>
<%--        "Computer Science",--%>
<%--        "Programming",--%>
<%--        "Statistics",--%>
<%--        "Medicine",--%>
<%--        "Nursing",--%>
<%--        "Law",--%>
<%--        "Political Science",--%>
<%--        "Astronomy",--%>
<%--        "Environmental Science",--%>
<%--        "Nutrition",--%>
<%--        "Physical Education",--%>
<%--        "Business",--%>
<%--        "Marketing",--%>
<%--        "Management",--%>
<%--        "Finance",--%>
<%--        "Accounting",--%>
<%--        "Entrepreneurship",--%>
<%--        "Communication",--%>
<%--        "Public Speaking",--%>
<%--        "Debate",--%>
<%--        "Ethics",--%>
<%--        "Cultural Studies",--%>
<%--        "Anthropology",--%>
<%--        "Archaeology",--%>
<%--        "Paleontology",--%>
<%--        "Mythology",--%>
<%--        "Religion",--%>
<%--        "World Religions",--%>
<%--        "Ethics",--%>
<%--        "Leadership",--%>
<%--        "Teamwork",--%>
<%--        "Time Management",--%>
<%--        "Goal Setting",--%>
<%--        "Stress Management",--%>
<%--        "Mindfulness",--%>
<%--        "Motivation",--%>
<%--        "Self-discipline",--%>
<%--        "Creativity",--%>
<%--        "Critical Thinking",--%>
<%--        "Problem Solving",--%>
<%--        "Decision Making",--%>
<%--        "Emotional Intelligence",--%>
<%--        "Cybersecurity",--%>
<%--        "Artificial Intelligence",--%>
<%--        "Machine Learning",--%>
<%--        "Robotics",--%>
<%--        "Cryptocurrency",--%>
<%--        "Blockchain",--%>
<%--        "Internet of Things (IoT)",--%>
<%--        "Virtual Reality (VR)",--%>
<%--        "Augmented Reality (AR)",--%>
<%--        "Renewable Energy",--%>
<%--        "Climate Change",--%>
<%--        "Sustainable Development",--%>
<%--        "Green Living",--%>
<%--        "Yoga",--%>
<%--        "Meditation",--%>
<%--        "Fitness",--%>
<%--        "Nutrition",--%>
<%--        "Healthy Living",--%>
<%--        "Cooking",--%>
<%--        "Gardening",--%>
<%--        "DIY Projects",--%>
<%--        "Photography",--%>
<%--        "Film",--%>
<%--        "Animation",--%>
<%--        "Game Design",--%>
<%--        "Graphic Design",--%>
<%--        "Web Development",--%>
<%--        "Mobile App Development",--%>
<%--        "Social Media",--%>
<%--        "Influencer Marketing",--%>
<%--        "E-commerce",--%>
<%--        "Digital Marketing",--%>
<%--        "Stock Market",--%>
<%--        "Real Estate",--%>
<%--        "Travel",--%>
<%--        "Food",--%>
<%--        "Fashion",--%>
<%--        "Parenting"--%>
<%--    ];--%>
<%--    const resultBox = document.querySelector(".result-box");--%>
<%--    const inputBox = document.querySelector("#input-box");--%>

<%--    inputBox.onkeyup = function () {--%>
<%--        let result = [];--%>
<%--        let input = inputBox.value;--%>
<%--        if (input.length) {--%>
<%--            result = availablekeywords.filter((data) => {--%>
<%--                return data.toLocaleLowerCase().includes(input.toLocaleLowerCase());--%>
<%--            });--%>
<%--            console.log(result)--%>
<%--        }--%>
<%--        if (result.length === 0) {--%>
<%--            resultBox.innerHTML = ""; // Clear the result box if no matches found--%>
<%--        } else {--%>
<%--            display(result);--%>
<%--        }--%>
<%--        if (!input.length) {--%>
<%--            resultBox.innerHTML = "";--%>
<%--        }--%>
<%--    }--%>
<%--    function display(result) {--%>
<%--        const slicedResult = result.slice(0, 6);--%>
<%--        const content = slicedResult.map(function (list) {--%>
<%--            return '<li onclick="selectInput(this)"><i class="fa-solid fa-magnifying-glass"></i> ' + list + '</li>';--%>
<%--        });--%>
<%--        resultBox.innerHTML = '<ul>' + content.join('') + '</ul>';--%>
<%--    }--%>
<%--    function selectInput(list) {--%>
<%--        inputBox.value = element.textContent;--%>
<%--        resultBox.innerHTML = "";--%>
<%--    }--%>
<%--</script>--%>
<!-- Footer -->
<%--<jsp:include page="../../../components/footer.jsp"></jsp:include>--%>
<!-- Footer -->
<%--<script src="./js/Search.js"></script>--%>
</body>
</html>
