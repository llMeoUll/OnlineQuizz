<%--
  Created by IntelliJ IDEA.
  User: khanh
  Date: 1/24/2024
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error page!</title>
    <link rel="stylesheet" href="../../../.././css/manageRoom/NotFound.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo96x96.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/6.5.1/css/all.min.css">
    <script src="${pageContext. request. contextPath}/webjars/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext. request. contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../../../components/header.jsp"/>

<div class="container text-center" style="margin-top: 96px">
    <c:if test="${requestScope.ExceededTimesDoTest ne null}">
        <p>${requestScope.ExceededTimesDoTest}</p>
    </c:if>

    <c:if test="${requestScope.NoTestOwnerRights ne null}">
        <p>${requestScope.NoTestOwnerRights}</p>
    </c:if>
    <c:if test="${requestScope.timeErrorMessage ne null}">
        <p>${requestScope.timeErrorMessage}</p>
    </c:if>
    <p>Error page!</p>
</div>

</body>
</html>
