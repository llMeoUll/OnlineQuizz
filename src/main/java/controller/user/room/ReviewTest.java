package controller.user.room;

import dao.RoomDBContext;
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
        RoomDBContext rDb = new RoomDBContext();
        currentTest.setRoom(rDb.getRoomById(currentTest.getRoom()));
        // list question and this answer
        ArrayList<Question> listResultQuestionAnswer = tDb.getListResultQuestionAnswer(testId, attempt, userId);
        // list question of this test
        ArrayList<Question> listQuestions = tDb.getListQuestionsOfTest(currentTest);
        for (Question question : listQuestions) {
            if (question.getType().getTypeName().equals("True/False")) {
                QuestionOption opt1 = new QuestionOption();
                opt1.setOptContent("True");
                QuestionOption opt2 = new QuestionOption();
                opt2.setOptContent("False");
                ArrayList<QuestionOption> opts = new ArrayList<>();
                opts.add(opt1);
                opts.add(opt2);
                question.setQuestionOptions(opts);
            } else if (question.getType().getTypeName().equals("Multiple choice")) {
                ArrayList<QuestionOption> opts = question.getQuestionOptions();
                // add answer to option
                QuestionOption answer = new QuestionOption();
                answer.setOptContent(question.getAnswer());
                opts.add(answer);
                question.setQuestionOptions(opts);
            } else if (question.getType().getTypeName().equals("Essay")) {
                QuestionOption opt = new QuestionOption();
                for (Question q : listResultQuestionAnswer) {
                    if (q.getQId() == question.getQId()) {
                        opt.setOptContent(q.getAnswer());
                        break;
                    }
                }
                ArrayList<QuestionOption> opts = new ArrayList<>();
                opts.add(opt);
                question.setQuestionOptions(opts);
            }
        }
        // close connection
        try {
            tDb.closeConnection();
            rDb.closeConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("currentTest", currentTest);
        request.setAttribute("listResultQuestionAnswer", listResultQuestionAnswer);
        request.setAttribute("listQuestions", listQuestions);
        request.getRequestDispatcher("../../../view/user/room/ReviewTest.jsp").forward(request, response);
        // else => don't permission

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
