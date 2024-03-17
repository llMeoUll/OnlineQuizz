package controller.user.room;

import dao.TestDBContext;
import entity.Question;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

public class ReviewTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// add feature 52 /user/room/test/reviewtest?testId=?&&attempt1=?&&userId=?
        int testId = Integer.parseInt(request.getParameter("testId"));
        int attempt = Integer.parseInt(request.getParameter("attempt"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        // if userId == userLogged || userId == uid (chủ room) - cái này tự join tự get
        TestDBContext tDb = new TestDBContext();
        Test currentTest = new Test();
        currentTest.setTestId(testId);
        currentTest = tDb.getTestById(currentTest);
        request.setAttribute("currentTest", currentTest);
        // list question of this test
        ArrayList<Question> listQuestions = new ArrayList<>();
        listQuestions = tDb.getListQuestionsOfTest(currentTest);

        // list question and this answer
        ArrayList<Question> listResultQuestionAnswer = tDb.getListResultQuestionAnswer(testId, attempt, userId);
        request.setAttribute("listResultQuestionAnswer", listResultQuestionAnswer);
        request.setAttribute("listQuestions", listQuestions);
        request.getRequestDispatcher("../../../view/user/room/ReviewTest.jsp").forward(request, response);
        // else => don't permission

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
