package controller.user.room.test;

import dao.QuestionDBContext;
import dao.SetDBContext;
import entity.Set;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class AddQuestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("sets") != null){
            session.removeAttribute("sets");
        }
        SetDBContext setDBContext = new SetDBContext();
        User user = (User) session.getAttribute("user");
        ArrayList<Set> allSets = setDBContext.list(user);
        ArrayList<Set> setAndQuestions = new ArrayList<>();

        for (Set set : allSets) {
            Set entity = new Set();
            entity.setSId(set.getSId());
            entity.setSName(set.getSName());
            QuestionDBContext questionDBContext = new QuestionDBContext();
            entity.setQuestions(questionDBContext.list(set.getSId()));
            setAndQuestions.add(entity);
        }
        // close connection
        try {
            setDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("sets", setAndQuestions);
        request.getRequestDispatcher("../../../.././view/user/room/test/AddQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questions = request.getParameter("questions");

        if(questions != null && questions.length() > 0){
            String[] questionArray = questions.split(",");

            int[] intQuestionArray = new int[questionArray.length];
            for (int i = 0; i < questionArray.length; i++) {
                intQuestionArray[i] = Integer.parseInt(questionArray[i]);
            }
            HttpSession session = request.getSession();
            if(session.getAttribute("questions") != null){
                session.removeAttribute("questions");
            }
            session.setAttribute("questions", intQuestionArray);
            response.sendRedirect("./review");
        } else {
            request.setAttribute("error", "Please select at least one question");
            request.getRequestDispatcher("../../../.././view/user/room/test/AddQuestion.jsp").forward(request, response);
        }


    }
}
