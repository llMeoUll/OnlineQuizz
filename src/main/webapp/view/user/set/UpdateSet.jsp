<%--
  Created by IntelliJ IDEA.
  User: vanli
  Date: 2/20/2024
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Set | Quizzicle</title>
    <link rel="stylesheet" href="../.././css/CreateSet.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<div id="create-set-content" style="margin-top: 64px">
    <form action="./update?set-id=${requestScope.set.getSId()}" method="post">
        <div class="container-fluid bg-white p-4 d-flex align-items-center justify-content-between sticky-top">
            <h3 class="d-inline mx-3">Update set</h3>
            <button type="submit" class="btn btn-primary mx-3">Update</button>
        </div>
        <div class="container d-flex flex-column my-3 z-1">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title"
                       value="${requestScope.set.getSName()}" required/>
                <label for="title">Title</label>
            </div>
            <div class="form-floating mb-3">
            <textarea type="text" class="form-control" id="description" name="description"
                      placeholder="Add a description"
                      style="height: 100px">${requestScope.set.description}</textarea>
                <label for="description">Description</label>
            </div>
            <div class="form-floating mb-3" id="hashtag-input">
                <input type="text" class="form-control" id="hashtag-text"
                       placeholder="Enter hashtag (comma-separated)"/>
                <label for="hashtag-text">Hashtags</label>
                <div class="mt-4" id="hashtag-container">
                    <c:forEach var="hashtag" items="${requestScope.set.hashTags}">
                        <input class="btn btn-outline-primary m-1 hashtag" name="hashtags" value="${hashtag.name}"
                               type="text" style="width: ${hashtag.name.length() + 4}ch;">
                    </c:forEach>
                </div>
            </div>

            <div class="form-floating mb-3">
                <select class="form-select" id="privacy" name="privacy">
                    <c:if test="${requestScope.set.isPrivate() eq false}">
                        <option value="public" selected>Public</option>
                        <option value="private">Private</option>
                    </c:if>
                    <c:if test="${requestScope.set.isPrivate() eq true}">
                        <option value="public">Public</option>
                        <option value="private" selected>Private</option>
                    </c:if>
                </select>
                <label for="privacy">Privacy</label>
            </div>

            <div id="question-list" class="d-flex flex-column">
                <c:forEach var="question" items="${requestScope.set.questions}" varStatus="QuestionsLoop">
                    <div class="card mb-3 question" id="question-container-${QuestionsLoop.count}">
                        <div class="card-header">
                            <div class="d-flex align-items-center justify-content-between mb-1 mx-2">
                                <div>
                                    <span class="index">${QuestionsLoop.count}</span>
                                </div>
                                <div>
                            <span role="button" class="text-primary trash-icon"
                                  onclick="handleRemoveQuestion(${QuestionsLoop.count})">
                                <i class="fa-solid fa-trash"></i>
                            </span>
                                </div>
                            </div>
                            <div class="form-floating">
                                <select class="form-select question-type" id="question-type-${QuestionsLoop.count}"
                                        name="question-type-${QuestionsLoop.count}"
                                        onchange="handleSelectType(${QuestionsLoop.count});">
                                    <option value="Multiple choice"
                                            <c:if test="${question.getType().typeName eq 'Multiple choice'}">selected</c:if>>
                                        Multiple choice
                                    </option>

                                    <option value="True/False"
                                            <c:if test="${question.getType().typeName eq 'True/False'}">selected</c:if>>
                                        True/False
                                    </option>
                                    <option value="Essay"
                                            <c:if test="${question.getType().typeName eq 'Essay'}">selected</c:if>>
                                        Essay
                                    </option>
                                </select>
                                <label class="question-type-label"
                                       for="question-type-${QuestionsLoop.count}">Type</label>
                            </div>
                        </div>
<%--                        multiple choice--%>
                        <div class="card-body multiple-choice" id="multiple-choice-${QuestionsLoop.count}"
                                <c:if test="${question.getType().typeName ne 'Multiple choice'}">
                                    style="display: none;"
                                </c:if>
                        >
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control mul-question"
                                       name="mul-question-${QuestionsLoop.count}"
                                       id="mul-question-${QuestionsLoop.count}"
                                       placeholder="Enter a question"
                                        <c:if test="${question.getType().typeName eq 'Multiple choice'}">
                                            required
                                            value="${question.question}"
                                        </c:if>
                                />
                                <label class="mul-question-label" for="mul-question-${QuestionsLoop.count}">Question</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control mul-answer"
                                       id="mul-answer-${QuestionsLoop.count}"
                                       name="mul-answer-${QuestionsLoop.count}"
                                       placeholder="Enter a answer"
                                        <c:if test="${question.getType().typeName eq 'Multiple choice'}">
                                            required
                                            value="${question.answer}"
                                        </c:if>
                                />
                                <label class="mul-answer-label" for="mul-answer-${QuestionsLoop.count}">Answer</label>
                            </div>
                            <div class="opt-container" id="opt-container-${QuestionsLoop.count}">
                                <c:if test="${question.getType().typeName eq 'Multiple choice'}">
                                    <input type="hidden" name="number-opt-mul-${QuestionsLoop.count}"
                                           id="number-opt-mul-${QuestionsLoop.count}" value="${question.questionOptions.size()}">
                                    <c:forEach var="option" items="${question.questionOptions}" varStatus="OptionLoop">
                                        <div class="input-group mb-3 align-items-center opt">
                                                <input type="text" name="mul-question-${QuestionsLoop.count}-opt-${OptionLoop.count}"
                                                       class="form-control"
                                                        value="${option.optContent}"
                                                       placeholder="Option 1" required/>
                                            <span class="text-primary mx-1 xmark-icon" role="button"
                                                  onclick="handleRemoveOpt(${QuestionsLoop.count},${OptionLoop.count});">
                                                <i class="fa-solid fa-xmark"></i>
                                            </span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${question.getType().typeName ne 'Multiple choice'}">
                                    <input type="hidden" name="number-opt-mul-${QuestionsLoop.count}"
                                           id="number-opt-mul-${QuestionsLoop.count}">
                                    <div class="input-group mb-3 align-items-center opt">
                                        <input type="text" name="mul-question-${QuestionsLoop.count}-opt-1"
                                               class="form-control"
                                               placeholder="Option 1"/>
                                        <span class="text-primary mx-1 xmark-icon" role="button"
                                              onclick="handleRemoveOpt(${QuestionsLoop.count},1);">
                                                <i class="fa-solid fa-xmark"></i>
                                        </span>
                                    </div>
                                </c:if>

                            </div>
                            <div class="text-center">
                                <span role="button" class="text-primary btn-add-opt" onclick="handleAddOpt(${QuestionsLoop.count});">
                                    <i class="fa-solid fa-circle-plus"></i>
                                </span>
                            </div>
                        </div>
<%--                        True/False question--%>
                        <div class="card-body true-false" id="true-false-${QuestionsLoop.count}"
                                <c:if test="${question.getType().typeName ne 'True/False'}">
                                    style="display: none;"
                                </c:if>
                                <c:if test="${question.getType().typeName eq 'True/False'}">
                                    style="display: block;"
                                </c:if>
                        >
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control tf-question" id="tf-question-${QuestionsLoop.count}"
                                       name="tf-question-${QuestionsLoop.count}"
                                       placeholder="Enter a question"
                                        <c:if test="${question.getType().typeName eq 'True/False'}">
                                            required
                                            value="${question.question}"
                                        </c:if>
                                />
                                <label class="tf-question-label" for="tf-question-${QuestionsLoop.count}">Question</label>
                            </div>
                            <div class="form-floating mb-3">
                                <select class="form-select tf-answer" id="tf-answer-${QuestionsLoop.count}" name="tf-answer-${QuestionsLoop.count}">
                                    <option value="true"
                                        <c:if test="${question.getType().typeName eq 'True/False' && question.answer eq 'true'}">
                                            selected
                                        </c:if>
                                    >True</option>
                                    <option value="false"
                                            <c:if test="${question.getType().typeName eq 'True/False' && question.answer eq 'false'}">
                                                selected
                                            </c:if>
                                    >False</option>
                                </select>
                                <label class="tf-answer-label" for="tf-answer-${QuestionsLoop.count}">Answer</label>
                            </div>
                        </div>

                        <div class="card-body essay" id="essay-${QuestionsLoop.count}"
                                <c:if test="${question.getType().typeName ne 'Essay'}">
                                    style="display: none;"
                                </c:if>
                                <c:if test="${question.getType().typeName eq 'Essay'}">
                                    style="display: block;"
                                </c:if>

                        >
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control essay-question" id="essay-question-${QuestionsLoop.count}"
                                       name="essay-question-${QuestionsLoop.count}" placeholder="Enter a question"
                                        <c:if test="${question.getType().typeName eq 'Essay'}">
                                            required
                                            value="${question.question}"
                                        </c:if>
                                />
                                <label class="essay-question-label" for="essay-question-${QuestionsLoop.count}">Question</label>
                            </div>
                            <textarea class="form-control essay-answer" id="essay-answer-${QuestionsLoop.count}" name="essay-answer-${QuestionsLoop.count}"
                                      placeholder="Enter a answer"
                                      style="height: 100px"><c:if test="${question.getType().typeName eq 'Essay'}">${question.answer}</c:if></textarea>
                        </div>
                    </div>
                </c:forEach>

                <button type="button" onclick="handleAddQuestion();" class="btn btn-outline-primary">+ADD QUESTION
                </button>
            </div>
            <input type="hidden" name="number-of-question" id="number-of-question">
            <button type="submit" class="btn btn-primary mt-3">Update</button>
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
<script src="${pageContext.request.contextPath}/js/CreateSet.js"></script>
</body>
</html>
