package controller.user.profile;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateUsername extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("errorUsername", "Username is required");
            request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
            return;
        }
        UserDBContext userDBContext = new UserDBContext();
        if(userDBContext.checkUsername(username)){
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            user.setUsername(username);
            userDBContext.updateUsername(user);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/user/profile/update");
        }else {
            request.setAttribute("errorUsername", "Username is already taken");
            request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
        }
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
