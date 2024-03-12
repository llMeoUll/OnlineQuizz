package controller.user.set;


import dao.*;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // listSet
//        int setID = Integer.parseInt(request.getParameter("setID"));
//        QuestionDBContext questionDBContext = new QuestionDBContext();
//        request.setAttribute("listQuestion", questionDBContext.list(setID));
//        request.setAttribute("setID", setID);

        int setID = 21;
        // user(avatar, name)
        // Comment(comment_id, content, reply_id, (count)likes, (count)unlikes, time)
//        User u = (User) session.getAttribute("user");
//        int id = u.getId();
//        UserDBContext udb = new UserDBContext();
//        User user = udb.get(id);

        //comment

        request.getRequestDispatcher("../.././view/user/set/GetSet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int setId = Integer.parseInt(request.getParameter("set-id"));
        String description = request.getParameter("description");
        String[] hashTags = request.getParameterValues("hashtags") != null ? request.getParameterValues("hashtags") : new String[0];
        boolean privacy = request.getParameter("privacy") != null && request.getParameter("privacy").equals("private") ? true : false;
        Set set = new Set();
        set.setSId(setId);
        if (hashTags.length != 0) {
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
        set.setPrivate(privacy);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.getWriter().println("You are not logged in");
            return;
        }
        set.setUser(user);

        ArrayList<Question> questions = new ArrayList<>();
        int numberOfQuestions = Integer.parseInt(request.getParameter("number-of-question"));
        for (int i = 1; i <= numberOfQuestions; i++) {
            Type type = new Type();
            type.setTypeName(request.getParameter("question-type-" + i));
            type.setTypeId(getIdType(type.getTypeName()));
            Question question = new Question();
            question.setType(type);
            switch (type.getTypeName()) {
                case "Multiple choice":
                    String mulQuestion = request.getParameter("mul-question-" + i);
                    String mulAnswer = request.getParameter("mul-answer-" + i);
                    int numberOfOpt = Integer.parseInt(request.getParameter("number-opt-mul-" + i));
                    ArrayList<QuestionOption> options = new ArrayList<>();
                    for (int j = 1; j <= numberOfOpt; j++) {
                        QuestionOption option = new QuestionOption();
                        option.setOptContent(request.getParameter("mul-question-" + i + "-opt-" + j));
                        options.add(option);
                    }
                    question.setQuestion(mulQuestion);
                    question.setAnswer(mulAnswer);
                    question.setQuestionOptions(options);
                    questions.add(question);
                    break;
                case "True/False":
                    String tfQuestion = request.getParameter("tf-question-" + i);
                    String tfAnswer = request.getParameter("tf-answer-" + i);
                    question.setQuestion(tfQuestion);
                    question.setAnswer(tfAnswer);
                    questions.add(question);
                    break;
                case "Essay":
                    String essayQuestion = request.getParameter("essay-question-" + i);
                    String essayAnswer = request.getParameter("essay-answer-" + i);
                    question.setQuestion(essayQuestion);
                    question.setAnswer(essayAnswer);
                    questions.add(question);
                    break;

            }
        }
        set.setQuestions(questions);
        SetDBContext setDB = new SetDBContext();
        try {
            setDB.update(set);
            response.getWriter().println("Update set successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIdType(String typeName) {
        TypeDBContext typeDBContext = new TypeDBContext();
        ArrayList<Type> types = typeDBContext.list();
        for (Type type : types) {
            if (type.getTypeName().equals(typeName)) {
                return type.getTypeId();
            }
        }
        return -1;
    }
}
