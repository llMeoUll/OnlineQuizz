<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 3/2/2024
  Time: 6:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add question to the test</title>
    <link rel="stylesheet" href="../../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../.././css/CreateSet.css">
    <link rel="stylesheet" href="../../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <script src="../../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h3 class="text-center">Add question to the test</h3>
<ul class="nav nav-tabs justify-content-center mb-3" id="choose-tab" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" id="your-set-tab" data-bs-toggle="tab" data-bs-target="#your-set-tab-pane"
                type="button" role="tab" aria-controls="your-set-tab-pane" aria-selected="true">Choose your set
        </button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link disabled" type="button" role="tab">Or</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="create-set-tab" data-bs-toggle="tab" data-bs-target="#create-set-tab-pane"
                type="button" role="tab" aria-controls="create-set-tab-pane" aria-selected="false">Create a new set
        </button>
    </li>
</ul>
<div class="tab-content" id="choose-tab-content">
    <%--        Choose your set--%>
    <div class="tab-pane fade show active" id="your-set-tab-pane" role="tabpanel" aria-labelledby="your-set-tab"
         tabindex="0">
        <div class="container-fluid">
            <div class="row d-flex">
                <%--                        set list container--%>
                <div class="nav flex-column nav-pills col-md-3" id="v-pills-tab" role="tablist"
                     aria-orientation="vertical">
                    <%--                            list set--%>
                    <table id="sets-table">
                        <c:forEach items="${sessionScope.sets}" var="set" varStatus="setLoop">
                            <tr>
                                <td>
                                    <button
                                            <c:if test="${setLoop.count eq 1}">class="nav-link my-1 active set"</c:if>
                                            <c:if test="${setLoop.count ne 1}">class="nav-link my-1 set"</c:if>
                                            id="v-pills-set-${setLoop.count}-tab"
                                            data-bs-toggle="pill"
                                            data-bs-target="#v-pills-set-${setLoop.count}" type="button" role="tab"
                                            aria-controls="v-pills-set-${setLoop.count}"
                                            aria-selected="true"
                                            onclick="switchTab('v-pills-set-${setLoop.count}-tab', 'v-pills-set-${setLoop.count}')">${set.getSName()}
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <%--                        list question--%>
                <div class="tab-content col-md-8" id="v-pills-tabContent">
                    <c:forEach items="${sessionScope.sets}" var="set" varStatus="setLoop">
                        <div
                                <c:if test="${setLoop.count eq 1}">class="tab-pane fade show active set-content"</c:if>
                                <c:if test="${setLoop.count ne 1}">class="tab-pane fade set-content"</c:if>
                                id="v-pills-set-${setLoop.count}" role="tabpanel"
                                aria-labelledby="v-pills-set-${setLoop.count}-tab" tabindex="0">
                            <div class="d-flex justify-content-between align-items-center">
                                <button type="button" class="btn btn-outline-primary mb-3"
                                        onclick="selectAll(${setLoop.count})">select all question
                                </button>
                                <button type="button" class="btn btn-outline-primary mb-3"
                                        onclick="deselectAll(${setLoop.count})">deselect all question
                                </button>
                            </div>
                            <c:forEach items="${set.getQuestions()}" var="question" varStatus="questionLoop">
                                <div class="card mb-3">
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <span>${questionLoop.count}</span>
                                        <input type="checkbox" class="question-checkbox-${setLoop.count}"
                                               name="question-ids"
                                                <c:if test="${sessionScope.questions ne null}">
                                                    <c:forEach items="${sessionScope.questions}" var="chosenQuestion">
                                                        <c:if test="${chosenQuestion eq question.getQId()}">
                                                            checked
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                               value="${question.getQId()}"/>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">Question: ${question.question}</p>
                                        <p class="card-text">Answer: ${question.answer}</p>
                                        <c:if test="${question.questionOptions.size() ne 0}">
                                            <c:forEach items="${question.getQuestionOptions()}" var="opt"
                                                       varStatus="optionLoop">
                                                <p>Option ${optionLoop.count}: ${opt.optContent}</p>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                    <form action="./add-question" method="post" id="add-question">
                        <input type="hidden" name="questions" value=""/>
                    </form>

                </div>
            </div>
        </div>
        <div class="container-fluid d-flex justify-content-between fixed-bottom pb-3 px-5">
            <a href="../create?roomId=${sessionScope.room.roomId}" class="btn btn-outline-primary mt-3">Back</a>
            <c:if test="${requestScope.error ne null}">
                <p class="text-danger">${requestScope.error}</p>
            </c:if>
            <button class="btn btn-outline-primary mt-3" onclick="submitFormAddQuestion()">Next</button>
        </div>
    </div>

    <%--        Create a new set--%>
    <div class="tab-pane fade" id="create-set-tab-pane" role="tabpanel" aria-labelledby="create-set-tab"
         tabindex="0">
        <div id="create-set-content" style="margin-top: 64px">
            <form action="../../.././set/create" method="post">
                <div class="container d-flex flex-column my-3 z-1">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title"
                               required/>
                        <label for="title">Title</label>
                    </div>
                    <div class="form-floating mb-3">
            <textarea type="text" class="form-control" id="description" name="description"
                      placeholder="Add a description"
                      style="height: 100px"></textarea>
                        <label for="description">Description</label>
                    </div>
                    <div class="form-floating mb-3" id="hashtag-input">
                        <input type="text" class="form-control" id="hashtag-text"
                               placeholder="Enter hashtag (comma-separated)"/>
                        <label for="hashtag-text">Hashtags</label>
                        <div class="mt-4" id="hashtag-container"></div>
                    </div>

                    <div class="form-floating mb-3">
                        <select class="form-select" id="privacy" name="privacy">
                            <option value="public">Public</option>
                            <option value="private">Private</option>
                        </select>
                        <label for="privacy">Privacy</label>
                    </div>

                    <div id="question-list" class="d-flex flex-column">
                        <div class="card mb-3 question" id="question-container-1">
                            <div class="card-header">
                                <div class="d-flex align-items-center justify-content-between mb-1 mx-2">
                                    <div>
                                        <span class="index">1</span>
                                    </div>
                                    <div>
                            <span role="button" class="text-primary trash-icon" onclick="handleRemoveQuestion(1)">
                                <i class="fa-solid fa-trash"></i>
                            </span>
                                    </div>
                                </div>
                                <div class="form-floating">
                                    <select class="form-select question-type" id="question-type-1"
                                            name="question-type-1"
                                            onchange="handleSelectType(1);">
                                        <option value="Multiple choice">Multiple choice</option>
                                        <option value="True/False">True/False</option>
                                        <option value="Essay">Essay</option>
                                    </select>
                                    <label class="question-type-label" for="question-type-1">Type</label>
                                </div>
                            </div>

                            <div class="card-body multiple-choice" id="multiple-choice-1">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control mul-question" name="mul-question-1"
                                           id="mul-question-1"
                                           placeholder="Enter a question" required/>
                                    <label class="mul-question-label" for="mul-question-1">Question</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control mul-answer" id="mul-answer-1"
                                           name="mul-answer-1"
                                           placeholder="Enter a answer" required/>
                                    <label class="mul-answer-label" for="mul-answer-1">Answer</label>
                                </div>
                                <div class="opt-container" id="opt-container-1">
                                    <input type="hidden" name="number-opt-mul-1" id="number-opt-mul-1">
                                    <div class="input-group mb-3 align-items-center opt">
                                        <input type="text" name="mul-question-1-opt-1" class="form-control"
                                               placeholder="Option 1" required/>
                                        <span class="text-primary mx-1 xmark-icon" role="button"
                                              onclick="handleRemoveOpt(1,1);">
                                <i class="fa-solid fa-xmark"></i>
                            </span>
                                    </div>

                                </div>
                                <div class="text-center">
                        <span role="button" class="text-primary btn-add-opt" onclick="handleAddOpt(1);"><i
                                class="fa-solid fa-circle-plus"></i></span>
                                </div>
                            </div>

                            <div class="card-body true-false" id="true-false-1" style="display: none;">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control tf-question" id="tf-question-1"
                                           name="tf-question-1"
                                           placeholder="Enter a question"/>
                                    <label class="tf-question-label" for="tf-question-1">Question</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <select class="form-select tf-answer" id="tf-answer-1" name="tf-answer-1">
                                        <option value="True">True</option>
                                        <option value="False">False</option>
                                    </select>
                                    <label class="tf-answer-label" for="tf-answer-1">Answer</label>
                                </div>
                            </div>

                            <div class="card-body essay" id="essay-1" style="display: none;">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control essay-question" id="essay-question-1"
                                           name="essay-question-1" placeholder="Enter a question"/>
                                    <label class="essay-question-label" for="essay-question-1">Question</label>
                                </div>
                                <textarea class="form-control essay-answer" id="essay-answer-1" name="essay-answer-1"
                                          placeholder="Enter a answer"
                                          style="height: 100px"></textarea>
                            </div>
                        </div>
                        <button type="button" onclick="handleAddQuestion();" class="btn btn-outline-primary">+ADD
                            QUESTION
                        </button>
                    </div>
                    <input type="hidden" name="number-of-question" id="number-of-question">
                    <button type="submit" class="btn btn-primary mt-3">Create</button>
                </div>

            </form>

        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="submit-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body text-danger">
            You can't next step! you must have at least 1 questions.
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../../../.././js/AddQuestion.js"></script>
<script src="../../../.././js/CreateSet.js"></script>
</body>
</html>
