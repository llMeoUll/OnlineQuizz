package controller.user.authenticate;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.Email;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // check if email and password is not null
        if (email == null && password == null) {
            request.setAttribute("error", "Email/Password is invalid!");
            request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
            return;
        }
        UserDBContext db = new UserDBContext();
        email = email.trim();
        password = password.trim();
        // check if email is registered
        if (db.checkEmail(email)) {
            // close connection
            try {
                db.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("error", "Email is not registered!");
            request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
            return;
        }
        User loggedUser = db.get(email, password);
        HttpSession session = request.getSession();
        if (loggedUser == null) {// check if email and password is correct
            // close connection
            try {
                db.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("error", "Email/Password is invalid!");
            request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
            return;
        } else if (!loggedUser.isVerified()) { // check if email is verified
            session.setAttribute("user", loggedUser);
            // Tạo nội dung email
            String subject = "Verify your email address";
            String content = "Please confirm that you want to use this email as your Quizzicle account email address";
            // Gửi email xác nhận
            Email sendEmail = new Email();
            String verifyType = "login-verifying-email";
            sendEmail.sendVerifyCode(request, loggedUser.getEmail(), subject, content, verifyType);
            String uri = request.getRequestURI();
            session.setAttribute("uri", uri);
            session.setAttribute("verifyType", verifyType);
            response.sendRedirect(request.getContextPath() + "/verify-code");
        } else {
            session.setAttribute("user", loggedUser);
            response.sendRedirect(request.getContextPath() + "/home");
        }
        // close connection
        try {
            db.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
