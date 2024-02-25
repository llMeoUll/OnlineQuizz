<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 2/20/2024
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Set | Quizzicle</title>
    <link rel="stylesheet" href="../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../.././css/CreateSet.css">
    <link rel="stylesheet" href="../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%--header--%>
<div class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">

</div>
<%--content--%>
<div id="create-set-content" style="margin-top: 64px">
    <form action="./create" method="post">
        <div class="container-fluid bg-white p-4 d-flex align-items-center justify-content-between sticky-top z-3">
            <h3 class="d-inline mx-3">Update Set</h3>
            <button type="submit" class="btn btn-primary mx-3">Create</button>
        </div>
        <div class="container d-flex flex-column my-3 z-1">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title" required/>
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
                            <select class="form-select question-type" id="question-type-1" name="question-type-1"
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
                            <input type="text" class="form-control mul-answer" id="mul-answer-1" name="mul-answer-1"
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
                            <input type="text" class="form-control tf-question" id="tf-question-1" name="tf-question-1"
                                   placeholder="Enter a question"/>
                            <label class="tf-question-label" for="tf-question-1">Question</label>
                        </div>
                        <div class="form-floating mb-3">
                            <select class="form-select tf-answer" id="tf-answer-1" name="tf-answer-1">
                                <option value="true">True</option>
                                <option value="false">False</option>
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
                <button type="button" onclick="handleAddQuestion();" class="btn btn-outline-primary">+ADD QUESTION
                </button>
            </div>
            <input type="hidden" name="number-of-question" id="number-of-question">
            <button type="submit" class="btn btn-primary mt-3">Create</button>
        </div>

    </form>
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div id="submit-toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body text-danger">
                You can't create set! you must have at least 2 questions.
            </div>
        </div>
    </div>
</div>
<script src="../.././js/CreateSet.js"></script>
</body>
</html>
