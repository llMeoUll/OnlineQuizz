package controller.user.profile;

import com.lambdaworks.crypto.SCryptUtil;
import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");

        UserDBContext userDBContext = new UserDBContext();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if the old password is correct
        if(userDBContext.checkPassword(user, oldPassword)) {
            // Check if the new password and confirm password are the same
            if(newPassword.equals(confirmPassword)) {
                // Encrypt the new password
                String encryptedPassword = SCryptUtil.scrypt(newPassword, 16, 16, 16);
                // Update the password
                userDBContext.updatePassword(user.getEmail(), encryptedPassword);
                response.sendRedirect(request.getContextPath() + "user/profile/update");
            } else {
                request.setAttribute("errorPassword", "New password and confirm password are not the same");
                request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorPassword", "Old password is incorrect");
            request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
        }

    }
}
