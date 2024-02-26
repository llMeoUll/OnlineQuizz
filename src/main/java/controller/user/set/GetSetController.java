package controller.user.set;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class GetSetController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        String setName = "Sample Set";
        String setDescription = "This is a sample flashcard set.";
        String hashtags = "#sample #flashcards";
        request.setAttribute("setName", setName);
        request.setAttribute("setDescription", setDescription);
        request.setAttribute("hashtags", hashtags);
        RequestDispatcher dispatcher = request.getRequestDispatcher("flashcardSet.jsp");
        dispatcher.forward(request, response);
        request.getRequestDispatcher("../view/user/GetSet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
