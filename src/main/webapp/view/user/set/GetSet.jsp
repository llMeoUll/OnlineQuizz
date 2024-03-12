<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Get set</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/ViewSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>

    <style>
        .flashcard-fl {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .flashcard {
            position: relative;
            width: 800px;
            height: 450px;
            background-color: #fff;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            cursor: pointer;
            perspective: 1000px;
        }

        .question,
        .answer {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            transition: transform 0.5s;
            backface-visibility: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: #333;
        }

        .question {
            transform: rotateY(0deg);
            background-color: #F0F8FF;
        }

        .answer {
            transform: rotateY(180deg);
            background-color: #FFF8DC;
        }

        .flashcard.flipped .question {
            transform: rotateY(160deg);
        }

        .flashcard.flipped .answer {
            transform: rotateY(0deg);
        }

        .hidden {
            display: none;
        }

        .controls {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .controls button {
            margin: 0 10px;
            padding: 10px 20px;
            font-size: 16px;
            background-color: #8936a0;
            color: #e2bdf3;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .controls button:hover {
            background-color: #8936a0;
        }


    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var flashcards = document.getElementsByClassName('flashcard');
            var currentIndex = 0;

            function showFlashcard(index) {
                if (index >= 0 && index < flashcards.length) {
                    for (var i = 0; i < flashcards.length; i++) {
                        flashcards[i].classList.add('hidden');
                    }
                    flashcards[index].classList.remove('hidden');
                    currentIndex = index;
                }
            }

            function showNextFlashcard() {
                var nextIndex = currentIndex + 1;
                if (nextIndex >= flashcards.length) {
                    nextIndex = 0;
                }
                showFlashcard(nextIndex);
            }

            function showPreviousFlashcard() {
                var prevIndex = currentIndex - 1;
                if (prevIndex < 0) {
                    prevIndex = flashcards.length - 1;
                }
                showFlashcard(prevIndex);
            }

            for (var i = 0; i < flashcards.length; i++) {
                flashcards[i].addEventListener('click', function () {
                    this.classList.toggle("flipped");
                });
            }

            var nextBtn = document.getElementById('nextBtn');
            var prevBtn = document.getElementById('prevBtn');

            nextBtn.addEventListener('click', showNextFlashcard);
            prevBtn.addEventListener('click', showPreviousFlashcard);

            showFlashcard(currentIndex);
        });
    </script>

</head>
<body>
<%--header--%>
<div class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">
    <main id="main">
        <%--        <h1 class="container h2" style="padding-top: 100px;">Flashcards: ${fl.getTitle()}</h1>--%>
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
            <script>
                function confirmDelete() {
                    var confirmation = window.confirm("Are you sure you want to delete set ${setID}?");
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
                </tbody>
                <style>
                    td {
                        word-wrap: break-word;
                        overflow-wrap: break-word;
                    }
                </style>

            </table>
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
            </style>
        </div>


    </main>

</div>
<%--content--%>
<div id="create-set-content" style="margin-top: 64px">

</div>
</body>
</html>

