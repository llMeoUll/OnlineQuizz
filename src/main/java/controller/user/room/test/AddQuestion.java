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
        session.setAttribute("sets", setAndQuestions);
        request.getRequestDispatcher("../../../.././view/user/room/test/AddQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questions = request.getParameter("questions");
        String[] questionArray = questions.split(",");
        HttpSession session = request.getSession();
        if(session.getAttribute("questions") != null){
            session.removeAttribute("questions");
        }
        session.setAttribute("questions", questionArray);
        response.sendRedirect("./review");
    }
}
