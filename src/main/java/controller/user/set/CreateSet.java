package controller.user.set;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class CreateSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../.././view/user/set/CreateSet.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
