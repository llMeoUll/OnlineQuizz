package controller.admin.user;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("uid"));
        UserDBContext userDBContext = new UserDBContext();
        User userForUpdate = userDBContext.get(userId);
        request.setAttribute("userForUpdate", userForUpdate);
        // Close connection
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../../view/admin/UpdateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("uid"));
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String givenName = request.getParameter("given-name");
        String familyName = request.getParameter("family-name");
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
        // Close connection
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../user");
    }
}
