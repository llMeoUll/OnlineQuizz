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
      transform: rotateY(180deg);
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
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .controls button:hover {
      background-color: #45a049;
    }

  </style>

</head>
<body>
<%--header--%>
<div class="fixed-top shadow z-2" style="height: 64px; background-color: #0d6efd">
  <main id="main" data-aos="fade-in">

    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs">
      <div class="container">
        <h2>All SET</h2>
      </div>
    </div><!-- End Breadcrumbs -->

    <div class="container" style="padding-top: 40px;"data-aos="fade-up">
      <section id="features" class="features">
        <div class="container" data-aos="fade-up">
          <div class="row" data-aos="zoom-in" data-aos-delay="100">
            <p>${count}</p>
            <p>${id}</p>
            <c:forEach var="c" items="${listSet}">
<%--              <div class="col-lg-3 col-md-4">--%>
<%--                <div class="icon-box">--%>
<%--                  <i class="ri-store-line" style="color: #ffbb2c;"></i>--%>
                  <h3><a href="./set/get?setID=${c.getSId()}">${c.getSName()}</a></h3>
<%--                </div>--%>
<%--              </div>--%>
            </c:forEach>
          </div>
        </div>

      </section>
    </div>
    <!-- ======= Courses Section ======= -->

  </main><!-- End #main -->


</div>
<%--content--%>
<div id="create-set-content" style="margin-top: 64px">

</div>
<%--<script src="../.././js/GetSet.js"></script>--%>
</body>
</html>

