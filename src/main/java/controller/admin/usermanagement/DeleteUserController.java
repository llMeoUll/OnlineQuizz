package controller.admin.usermanagement;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.User;

import java.io.IOException;

public class DeleteUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        int uid = Integer.parseInt(request.getParameter("uid"));
        User user = new User();
        user.setId(uid);
        userDBContext.delete(user);
        response.sendRedirect("user_management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
