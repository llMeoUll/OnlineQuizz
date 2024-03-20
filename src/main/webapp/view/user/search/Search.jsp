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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"></jsp:include>
<!-- header -->
<main style="height: 80vh; display: flex; flex-direction: column; align-items: center; justify-content: center; margin-top: 96px">
    <div class="nav-item mr-5 mt-1">
        <div style="text-align: center">
            <h1>Tìm kiếm Quizzcle</h1>
            <p>Tìm học phần lớp học, người dùng,...</p>
        </div>
        <form action="search" method="post" class="form-inline my-2 my-lg-0"
              style="width: 100%;">
            <div class="input-group input-group-lg" style="width: 50vw; height: 20px">
                <input name="query" type="text" class="form-control" aria-label="Large"
                       aria-describedby="inputGroup-sizing-lg" placeholder="Search..." value="${txtSearch}"
                       style="font-size: 20px; width: 80%;">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-secondary btn-number">
                        <i class="fa fa-search" style="margin-top: 2px;"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</main>

<jsp:include page="../../../components/footer.jsp"></jsp:include>
</body>
</html>
