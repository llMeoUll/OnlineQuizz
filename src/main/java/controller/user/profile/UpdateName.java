package controller.user.profile;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String givenName = request.getParameter("given-name");
        String familyName = request.getParameter("family-name");
        if (givenName == null || givenName.trim().isEmpty() || familyName == null || familyName.trim().isEmpty()) {
            request.setAttribute("errorName", "Given name and family name are required.");
            request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setGivenName(givenName);
        user.setFamilyName(familyName);
        session.setAttribute("user", user);

        UserDBContext userDBContext = new UserDBContext();
        userDBContext.updateName(user);
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/user/profile/update");
    }
}
