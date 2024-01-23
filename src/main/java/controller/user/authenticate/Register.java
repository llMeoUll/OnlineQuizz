package controller.user.authenticate;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;
import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

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
                if(db.checkUsername(userName)) {
                    if (password.equals(verifyPassword)) {
                        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
                        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
                        // Tạo một đối tượng User mới và thiết lập thông tin
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setGiven_name(firstName);
                        newUser.setFamily_name(lastName);
                        newUser.setPassword(generatedSecuredPasswordHash);
                        newUser.setUsername(userName);
                        db.insert(newUser);
                        response.sendRedirect("./verifyemail");
                    }else {
                        // Xử lý lỗi nếu mật khẩu và xác nhận mật khẩu không khớp
                        request.setAttribute("error", "Password and confirm password do not match!");
                        request.getRequestDispatcher("./view/Register.jsp").forward(request, response);
                    }
                } else {
                    // Xử lý lỗi nếu username đã tồn tại
                    request.setAttribute("error", "Username already exists!");
                    request.getRequestDispatcher("./view/Register.jsp").forward(request, response);
                }

            } else {
                // Xử lý lỗi nếu email đã tồn tại
                request.setAttribute("error", "Email already exists!");
                request.getRequestDispatcher("./view/Register.jsp").forward(request, response);
            }
        } else {
            // Xử lý lỗi nếu một số trường không có giá trị
            request.setAttribute("error", "Please fill out the form!");
            request.getRequestDispatcher("./view/Register.jsp").forward(request, response);
        }
    }
}
