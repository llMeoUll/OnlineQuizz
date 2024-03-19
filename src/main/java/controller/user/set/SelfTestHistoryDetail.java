package controller.user.set;

import dao.SelfTestDBContext;
import dao.SelfTestQuestionDBContext;
import dao.SetDBContext;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelfTestHistoryDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selfTestId = Integer.parseInt(request.getParameter("stid"));
        // get self test information
        SelfTestDBContext selfTestDBContext = new SelfTestDBContext();
        SelfTest selfTest = selfTestDBContext.get(selfTestId);
        //get set information
        SetDBContext setDBContext = new SetDBContext();
        Set set = setDBContext.get(selfTest.getSet().getSId());
        Set setInfor = new Set();
        setInfor.setSId(set.getSId());
        setInfor.setSName(set.getSName());
        setInfor.setDescription(set.getDescription());
        selfTest.setSet(setInfor);
        // get self test questions
        SelfTestQuestionDBContext selfTestQuestionDBContext = new SelfTestQuestionDBContext();
        ArrayList<SelfTestQuestion> selfTestQuestions = selfTestQuestionDBContext.list(selfTestId);
        // close connection
        try {
            selfTestQuestionDBContext.closeConnection();
            selfTestDBContext.closeConnection();
            setDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Question> questions = set.getQuestions();
        for(SelfTestQuestion stq : selfTestQuestions){
            for (Question question : questions) {
                if (question.getQId() == stq.getQuestion().getQId()) {
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
                        //remove answer from question
                        question.setQuestionOptions(opts);
                    } else if (question.getType().getTypeName().equals("Essay")) {
                        QuestionOption opt = new QuestionOption();
                        opt.setOptContent(question.getAnswer());
                        QuestionOption userAnswer = new QuestionOption();
                        userAnswer.setOptContent(stq.getAnswer());
                        ArrayList<QuestionOption> opts = new ArrayList<>();
                        opts.add(opt);
                        opts.add(userAnswer);
                        question.setQuestionOptions(opts);
                    }
                    stq.setQuestion(question);
                }

            }
        }
        request.setAttribute("selfTest", selfTest);
        request.setAttribute("selfTestQuestions", selfTestQuestions);
        request.getRequestDispatcher("../../../.././view/user/set/SelfTestHistoryDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
