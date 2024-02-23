package controller.user.set;

import dao.SetDBContext;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class CreateSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../.././view/user/set/CreateSet.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] hashTags = request.getParameterValues("hashtags");
        String privacy = request.getParameter("privacy");

        Set set = new Set();
        if(hashTags.length != 0){
            ArrayList<HashTag> hashTagList = new ArrayList<>();
            for (String hashTag : hashTags) {
                HashTag entity = new HashTag();
                entity.setName(hashTag);
                hashTagList.add(entity);
            }
            set.setHashTags(hashTagList);
        }
        set.setSName(title);
        set.setDescription(description);
        set.setPrivate(privacy.equals("private"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        set.setUser(user);

        ArrayList<Question> questions = new ArrayList<>();
        int numberOfQuestions = Integer.parseInt(request.getParameter("number-of-question"));
        for (int i = 1; i <= numberOfQuestions; i++) {
            Type type = new Type();
            type.setTypeName(request.getParameter("question-type-" + i));
            Question question = new Question();
            question.setType(type);
            switch (type.getTypeName()) {
                case "multiple":
                    String mulQuestion = request.getParameter("mul-question" + i);
                    String mulAnswer = request.getParameter("mul-answer" + i);
                    int numberOfOpt = Integer.parseInt(request.getParameter("number-opt-mul-" + i));
                    ArrayList<QuestionOption> options = new ArrayList<>();
                    for (int j = 1; j <= numberOfOpt; j++) {
                        QuestionOption option = new QuestionOption();
                        option.setOptContent(request.getParameter("mul-option-" + i + "-" + j));
                        options.add(option);
                    }
                    question.setQuestion(mulQuestion);
                    question.setAnswer(mulAnswer);
                    question.setQuestionOptions(options);
                    questions.add(question);
                    break;
                case "true/false":
                    String tfQuestion = request.getParameter("tf-question" + i);
                    String tfAnswer = request.getParameter("tf-answer" + i);
                    question.setQuestion(tfQuestion);
                    question.setAnswer(tfAnswer);
                    questions.add(question);
                    break;
                case "essay":
                    String essayQuestion = request.getParameter("essay-question" + i);
                    String essayAnswer = request.getParameter("essay-answer" + i) != null ? request.getParameter("essay-answer" + i) : "";
                    question.setQuestion(essayQuestion);
                    question.setAnswer(essayAnswer);
                    questions.add(question);
                    break;

            }
        }
        set.setQuestions(questions);
        SetDBContext setDBContext = new SetDBContext();
        setDBContext.insert(set);

    }
}
