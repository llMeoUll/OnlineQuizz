package controller.admin.user;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;

import java.io.IOException;

public class UserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        UserDBContext userDBContext = new UserDBContext();
        User user = userDBContext.get(Integer.parseInt(uid));
        request.setAttribute("user", user);
        request.getRequestDispatcher("../../view/admin/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
