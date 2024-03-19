package controller.user.profile;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateAvatar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String avatar = request.getParameter("avatarUrl").trim();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            user.setAvatar(avatar);
            UserDBContext userDBContext = new UserDBContext();
            userDBContext.updateAvatar(user);
            session.setAttribute("user", user);
            try {
                userDBContext.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("./update");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
