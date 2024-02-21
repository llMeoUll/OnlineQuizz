package controller.admin.user;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;

import java.io.IOException;

public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        int uid = Integer.parseInt(request.getParameter("uid"));
        User user = new User();
        user.setId(uid);
        userDBContext.delete(user);
        response.sendRedirect("../user");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
