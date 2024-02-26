package controller.user.room;

import dao.TestDBContext;
import entity.Question;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

// user/room/test/get?testId = ??
public class TestDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // currentTest
        int testId = Integer.parseInt(request.getParameter("testId"));
        TestDBContext tDb = new TestDBContext();
        Test currentTest = new Test();
        currentTest.setTestId(testId);
        currentTest = tDb.getTestById(currentTest);
        request.setAttribute("currentTest", currentTest);
        // list question of this test
        ArrayList<Question> listQuestions = new ArrayList<>();
        listQuestions = tDb.getListQuestionsOfTest(currentTest);
        request.setAttribute("listQuestions", listQuestions);
        request.getRequestDispatcher("../../../view/user/room/ViewTestDetail.jsp").forward(request, response);
    }
}
