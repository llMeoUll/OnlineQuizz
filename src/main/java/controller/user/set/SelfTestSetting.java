package controller.user.set;

import dao.SetDBContext;
import entity.Question;
import entity.QuestionOption;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SelfTestSetting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int setId = Integer.parseInt(request.getParameter("setId"));
        SetDBContext setDB = new SetDBContext();
        Set set = setDB.get(setId);
        HttpSession session = request.getSession();
        if (session.getAttribute("set") != null) {
            session.removeAttribute("set");
        }
        session.setAttribute("set", set);
        request.getRequestDispatcher("../.././view/user/set/SelfTestSetting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean typeTrueFalse = request.getParameter("type-true-false") != null;
        boolean typeMultipleChoice = request.getParameter("type-multiple-choice") != null;
        boolean typeEssay = request.getParameter("type-essay") != null;
        int numberQuestion = Integer.parseInt(request.getParameter("number-question"));
        // init list question by type
        ArrayList<Question> questions = new ArrayList<>();
        HttpSession session = request.getSession();
        Set set = (Set) session.getAttribute("set");
        // list question in set
        ArrayList<Question> setQuestions = set.getQuestions();
        Collections.shuffle(setQuestions);
        // get question by type
        for (Question question : setQuestions) {
            if (question.getType().getTypeName().equals("True/False") && typeTrueFalse) {
                QuestionOption opt1 = new QuestionOption();
                opt1.setOptContent("True");
                QuestionOption opt2 = new QuestionOption();
                opt2.setOptContent("False");
                ArrayList<QuestionOption> opts = new ArrayList<>();
                opts.add(opt1);
                opts.add(opt2);
                question.setQuestionOptions(opts);
                questions.add(question);
            } else if (question.getType().getTypeName().equals("Multiple choice") && typeMultipleChoice) {
                ArrayList<QuestionOption> opts = question.getQuestionOptions();
                // add answer to option
                QuestionOption answer = new QuestionOption();
                answer.setOptContent(question.getAnswer());
                opts.add(answer);
                //remove answer from question
                question.setAnswer(null);
                Collections.shuffle(opts);
                question.setQuestionOptions(opts);
                questions.add(question);
            } else if (question.getType().getTypeName().equals("Essay") && typeEssay) {
                questions.add(question);
            }
            if(questions.size() == numberQuestion) {
                break;
            }
        }
        if(session.getAttribute("questions") != null) {
            session.removeAttribute("questions");
        }
        session.setAttribute("questions", questions);
        response.sendRedirect("./self-test");
    }

}
