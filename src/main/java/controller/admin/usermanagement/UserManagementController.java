package controller.admin.usermanagement;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import entity.User;

@WebServlet(name = "UserManagementController", value = "/user_management")
public class UserManagementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list();
        request.setAttribute("users", users);
        request.getRequestDispatcher("view/pages/admin/UserManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
