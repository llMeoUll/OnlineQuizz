package controller.admin.usermanagement;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;

public class UpdateUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("uid"));
        UserDBContext userDBContext = new UserDBContext();
        User userForUpdate = userDBContext.getUserById(userId);
        request.setAttribute("userForUpdate", userForUpdate);
        request.getRequestDispatcher("../view/admin/UpdateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
