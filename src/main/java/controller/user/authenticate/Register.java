package controller.user.authenticate;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
import dao.UserDBContext;
import entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.Email;
import util.MailTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Random;


public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String verifyPassword = request.getParameter("verify-password");
        UserDBContext db = new UserDBContext();
        // Kiểm tra xem các trường có giá trị hay không
        if (firstName != null && lastName != null && userName != null && email != null && password != null && verifyPassword != null) {
            if (db.checkEmail(email)) {
                if (db.checkUsername(userName)) {
                    if (password.equals(verifyPassword)) {
                        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
                        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
                        // Tạo một đối tượng User mới và thiết lập thông tin
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setGivenName(firstName);
                        newUser.setFamilyName(lastName);
                        newUser.setPassword(generatedSecuredPasswordHash);
                        newUser.setUsername(userName);
                        HttpSession session = request.getSession();
                        session.setAttribute("user", newUser);
                        // Tạo nội dung email
                        String subject = "Verify your email address";
                        String content = "Please confirm that you want to use this email as your Quizzicle account email address";
                        // Gửi email xác nhận
                        Email sendEmail = new Email();
                        String verifyType = "verify-email";
                        sendEmail.sendVerifyCode(request, newUser.getEmail(), subject, content, verifyType);
                        // Thêm người dùng vào cơ sở dữ liệu
                        db.insert(newUser);
                        //set verify type to verify email
                        session.setAttribute("verifyType", verifyType);
                        response.sendRedirect("./verify-code");
                    } else {
                        // Xử lý lỗi nếu mật khẩu và xác nhận mật khẩu không khớp
                        request.setAttribute("error", "Password and confirm password do not match!");
                        request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
                    }
                } else {
                    // Xử lý lỗi nếu username đã tồn tại
                    request.setAttribute("error", "Username already exists!");
                    request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
                }

            } else {
                // Xử lý lỗi nếu email đã tồn tại
                request.setAttribute("error", "Email already exists!");
                request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
            }
        } else {
            // Xử lý lỗi nếu một số trường không có giá trị
            request.setAttribute("error", "Please fill out the form!");
            request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
        }
    }
}
