package controller.user.room;

import dao.TestDBContext;
import entity.Question;
import entity.QuestionOption;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<Question> listQuestions = tDb.getListQuestionsOfTest(currentTest);
        // get question by type
        for (Question question : listQuestions) {
            if (question.getType().getTypeName().equals("True/False")) {
                QuestionOption opt1 = new QuestionOption();
                opt1.setOptContent(question.getAnswer());
                ArrayList<QuestionOption> opts = new ArrayList<>();
                opts.add(opt1);
                question.setQuestionOptions(opts);
            } else if (question.getType().getTypeName().equals("Multiple choice")) {
                ArrayList<QuestionOption> opts = question.getQuestionOptions();
                // add answer to option
                QuestionOption answer = new QuestionOption();
                answer.setOptContent(question.getAnswer());
                opts.add(answer);
                //remove answer from question
                question.setQuestionOptions(opts);
            }
        }
        // close connection
        try {
            tDb.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("listQuestions", listQuestions);
        request.getRequestDispatcher("../../../view/user/room/ViewTestDetail.jsp").forward(request, response);
    }
}
