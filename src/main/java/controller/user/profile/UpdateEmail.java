package controller.user.profile;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.Email;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateEmail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("resend") != null && request.getParameter("resend").equals("true")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String verifyType = (String) session.getAttribute("verifyType");
            String subject = "Verify your email address";
            String content = "Please confirm that you want to use this email as your Quizzicle account email address";
            Email sendEmail = new Email();
            sendEmail.sendVerifyCode(request, user.getEmail(), subject, content, verifyType);
            response.sendRedirect(request.getContextPath() + "/verify-code");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDBContext userDBContext = new UserDBContext();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (userDBContext.checkEmail(email)) {
            user.setEmail(email);
            session.setAttribute("user", user);

            // Tạo nội dung email
            String subject = "Verify your email address";
            String content = "Please confirm that you want to use this email as your Quizzicle account email address";
            // Gửi email xác nhận
            Email sendEmail = new Email();
            String verifyType = "verify-email-update";
            sendEmail.sendVerifyCode(request, user.getEmail(), subject, content, verifyType);
            String uri = request.getRequestURI();
            session.setAttribute("uri", uri);
            session.setAttribute("verifyType", verifyType);
            response.sendRedirect(request.getContextPath() + "/verify-code");

        } else {
            request.setAttribute("errorEmail", "Email already exists");
            request.getRequestDispatcher("../../view/user/profile/UpdateProfile.jsp").forward(request, response);
        }
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
