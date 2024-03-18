package controller.user.set;

import dao.SelfTestDBContext;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class DoSelfTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../.././view/user/set/SelfTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
        Set set = (Set) session.getAttribute("set");
        User user = (User) session.getAttribute("user");
        SelfTest selfTest = new SelfTest();
        selfTest.setUser(user);
        selfTest.setSet(set);
        ArrayList<SelfTestQuestion> selfTestQuestions = new ArrayList<>();
        for(Question question : questions) {
            String answer = request.getParameter("answer-" + question.getQId());
            SelfTestQuestion selfTestQuestion = new SelfTestQuestion();
            selfTestQuestion.setQuestion(question);
            selfTestQuestion.setAnswer(answer);
            selfTestQuestions.add(selfTestQuestion);
        }

        ArrayList<Question> rawQuestions = new ArrayList<>();
        for (Question question : set.getQuestions()) {
            for(SelfTestQuestion selfTestQuestion : selfTestQuestions) {
                if (question.getQId() == selfTestQuestion.getQuestion().getQId()) {
                    rawQuestions.add(question);
                }
            }
        }
        int result = 0;
        for (Question question : rawQuestions) {
            for(SelfTestQuestion selfTestQuestion : selfTestQuestions) {
                if (question.getQId() == selfTestQuestion.getQuestion().getQId()) {
                    if (question.getAnswer().equalsIgnoreCase(selfTestQuestion.getAnswer())) {
                        result++;
                    }
                }
            }
        }
        selfTest.setResult(result);
        try {
            SelfTestDBContext selfTestDBContext = new SelfTestDBContext();
            selfTestDBContext.insert(selfTest, selfTestQuestions);
            session.removeAttribute("questions");
            session.removeAttribute("set");
            response.sendRedirect("./self-test/history?setId=" + set.getSId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
