package controller.admin.usermanagement;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
        int id = Integer.parseInt(request.getParameter("uid"));
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String givenName = request.getParameter("givenName");
        String familyName = request.getParameter("familyName");
        User param = new User();
        param.setEmail(email);
        param.setId(id);
        param.setUsername(username);
        param.setGivenName(givenName);
        param.setFamilyName(familyName);
        Date updatedAt = new Date();
        param.setUpdatedAt(new Timestamp(updatedAt.getTime()));
        UserDBContext userDBContext = new UserDBContext();
        userDBContext.update(param);
    }
}
