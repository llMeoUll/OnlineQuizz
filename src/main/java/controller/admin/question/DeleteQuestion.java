package controller.admin.question;

import dao.QuestionDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class DeleteQuestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDBContext questionDBContext = new QuestionDBContext();
        int qid = Integer.parseInt(request.getParameter("qid"));
        questionDBContext.delete(qid);
        response.sendRedirect("../question");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
