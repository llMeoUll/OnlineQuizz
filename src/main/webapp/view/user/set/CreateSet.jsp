<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create a new set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/CreateSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
<div class="container-fluid">
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
        <%--        <input name="number-of-question" placeholder="Enter number of question"/>--%>
        <%--        <h1>Create Questions</h1>--%>
        <label for="numberOfQuestions">Enter the number of questions:</label>
        <input type="number" id="numberOfQuestions" name="number-of-question" placeholder="Enter number of questions"
               min="1" value="5">

        <button type="button" onclick="generateQuestionList()">Generate Question List</button>

        <div id="questionList"></div>
        <%--        question template--%>
        <div class="card">
            <label for="questionType" class="form-label">Select the question type:</label>
            <select id="questionType" class="form-select" onchange="handleChooseType()">
                <option value="multipleChoice">Multiple Choice</option>
                <option value="trueFalse">True/False</option>
                <option value="essay">Essay</option>
            </select>

            <%--            multiple choice template--%>
            <div id="multipleChoiceContainer" class="card-body mpc">
                <label for="question">Question: </label>
                <input name="question" id="question"/>
                <label for="answer">Answer: </label>
                <input name="answer" id="answer"/>
                <br/>
                <%--                opt conntainer--%>
                <div id="opt-container">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            A
                        </label>
                        <input name="opt-1"/>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            B
                        </label>
                        <input name="opt-1"/>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            C
                        </label>
                        <input name="opt-1"/>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                            D
                        </label>
                        <input name="opt-1"/>
                    </div>
                </div>
            </div>

            <%--            true/false template--%>
            <div id="trueFalseContainer" class="card-body true-false" style="display: none;">
                <label for="question">Question: </label>
                <input name="question" id="question"/>
                <label for="tfa" class="form-label">Answer for question: </label>
                <select id="tfa" class="form-select" onchange="handleChooseType()">
                    <option value="multipleChoice">True</option>
                    <option value="trueFalse">False</option>

                </select>


            </div>

            <%--            Essay template--%>
            <div id="essayContainer" class="card-body essay" style="display: none;">
                <label for="question">Question: </label>
                <input name="question" id=""/>
                <label for="answer">Answer: </label>
                <textarea name="answer" id=""></textarea>
            </div>
        </div>

        <div class="container" name="list-questions">

        </div>
        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script>
    function generateQuestionList() {
        var numQuestions = document.getElementById('numberOfQuestions').value;
        var questionFormsContainer = document.getElementById('questionList');
        questionFormsContainer.innerHTML = '';

        for (var i = 0; i < numQuestions; i++) {
            var questionForm = document.createElement('div');
            questionForm.innerHTML = `
                <h3>Question ${i+1}</h3>
                <label for="question_${i}">Question:</label>
                <input type="text" id="question_${i}" name="question_${i}">
                <br>
                <label for="answer_${i}">Answer:</label>
                <input type="text" id="answer_${i}" name="answer_${i}">
                <br>
            `;
            questionFormsContainer.appendChild(questionForm);
        }
    }
</script>
<script src="../.././js/CreateSet.js"></script>
</body>
</html>
</html>