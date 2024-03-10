package controller.user.room.test;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ReviewBeforeCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../../.././view/user/room/test/ReviewTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] questions = request.getParameterValues("question-ids");
        float[] scores = new float[questions.length];
        for (int i = 0; i < questions.length; i++) {
            scores[i] = Float.parseFloat(request.getParameter("score-question-" + questions[i]));
            response.getWriter().println(scores[i] + " " + questions[i]);
        }

    }
}
