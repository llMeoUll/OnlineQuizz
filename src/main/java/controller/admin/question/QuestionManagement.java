package controller.admin.question;

import dao.QuestionDBContext;
import entity.Question;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDBContext questionDBContext = new QuestionDBContext();
        ArrayList<Question> questions = questionDBContext.list();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("../view/admin/QuestionManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
