package controller.user.authenticate;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.Email;

import java.io.IOException;

public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/authenticate/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (!email.isEmpty()) {
            UserDBContext db = new UserDBContext();
            if (!db.checkEmail(email)) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                String verifyType = "reset-password";
                Email emailUtil = new Email();
                emailUtil.sendVerifyCode(request, email, "Reset Password", "Please confirm that you want to reset your password.", verifyType);
                //set verify type to reset password
                session.setAttribute("verifyType", verifyType);
                response.sendRedirect("./verify-code");
            } else {
                request.setAttribute("error", "Email không tồn tại");
                request.getRequestDispatcher("./view/user/authenticate/ForgotPassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Email không được để trống");
            request.getRequestDispatcher("./view/user/authenticate/ForgotPassword.jsp").forward(request, response);
        }



    }
}
