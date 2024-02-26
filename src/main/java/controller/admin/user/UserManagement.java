package controller.admin.user;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import entity.User;

public class UserManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list();
        request.setAttribute("users", users);
        request.getRequestDispatcher("../view/admin/UserManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("user_email");
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list(userEmail);
        request.setAttribute("users", users);
        request.getRequestDispatcher("../view/admin/UserManagement.jsp").forward(request, response);
    }
}
