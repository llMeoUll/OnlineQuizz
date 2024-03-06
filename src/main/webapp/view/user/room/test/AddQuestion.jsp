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
    <title>Add set to the test</title>
    <link rel="stylesheet" href="../../.././webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../.././css/CreateSet.css">
    <link rel="stylesheet" href="../../.././webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="../../.././webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="../../.././webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <h1 class="text-center">Add set to the test</h1>
    <ul class="nav nav-tabs justify-content-center mb-3" id="choose-tab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="your-set-tab" data-bs-toggle="tab" data-bs-target="#your-set-tab-pane" type="button" role="tab" aria-controls="your-set-tab-pane" aria-selected="true">Choose your set</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="search-set-tab" data-bs-toggle="tab" data-bs-target="#search-set-tab-pane" type="button" role="tab" aria-controls="search-set-tab-pane" aria-selected="false">Search set</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="create-set-tab" data-bs-toggle="tab" data-bs-target="#create-set-tab-pane" type="button" role="tab" aria-controls="create-set-tab-pane" aria-selected="false">Create a new set</button>
        </li>
    </ul>
    <div class="tab-content" id="choose-tab-content">
<%--        Choose your set--%>
        <div class="tab-pane fade show active" id="your-set-tab-pane" role="tabpanel" aria-labelledby="your-set-tab" tabindex="0">
            <div class="container">
                <div class="row">
                    <div class="col-12 mb-3">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search set" aria-label="Search set" aria-describedby="your-set-search-btn">
                            <button class="btn btn-outline-secondary" type="button" id="your-set-search-btn"><i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="d-flex align-items-start justify-content-between">
<%--                        list set--%>
                        <div class="nav flex-column nav-pills col-md-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                            <button class="nav-link active mb-2" id="v-pills-set-1-tab" data-bs-toggle="pill" data-bs-target="#v-pills-set-1" type="button" role="tab" aria-controls="v-pills-set-1" aria-selected="true">Set 1</button>
                            <button class="nav-link mb-2" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Set 2</button>
                        </div>

<%--                        list question--%>
                        <div class="tab-content col-md-8" id="v-pills-tabContent">
                            <div class="tab-pane fade show active" id="v-pills-set-1" role="tabpanel" aria-labelledby="v-pills-set-1-tab" tabindex="0">
                                <div class="card mb-3">
                                    <div class="card-header d-flex justify-content-between align-items-center" >
                                        <span>Question 1</span>
                                        <input type="checkbox"/>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">Answer 1</p>
                                        <p class="card-text">Answer 2</p>
                                    </div>
                                </div>
                                <div class="card mb-3">
                                    <div class="card-header">
                                        <p class="card-title">Question 1</p>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">Answer 1</p>
                                        <p class="card-text">Answer 2</p>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab" tabindex="0">...</div>
                        </div>
                    </div>
        </div>
<%--        Search set--%>
        <div class="tab-pane fade" id="search-set-tab-pane" role="tabpanel" aria-labelledby="search-set-tab" tabindex="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search set" aria-label="Search set" aria-describedby="search-set-search-btn">
                            <button class="btn btn-outline-secondary" type="button" id="search-set-search-btn"><i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Set name</th>
                                    <th scope="col">Number of words</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Set 1</td>
                                    <td>10</td>
                                    <td>
                                        <button type="button" class="btn btn-primary">Add</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set 2</td>
                                    <td>20</td>
                                    <td>
                                        <button type="button" class="btn btn-primary">Add</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Set 3</td>
                                    <td>30</td>
                                    <td>
                                        <button type="button" class="btn btn-primary">Add</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
<%--        Create a new set--%>
        <div class="tab-pane fade" id="create-set-tab-pane" role="tabpanel" aria-labelledby="create-set-tab" tabindex="0">...</div>
    </div>
</body>
</html>
