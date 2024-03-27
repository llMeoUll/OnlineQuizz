package controller.user.authenticate;

import com.lambdaworks.crypto.SCryptUtil;
import dao.NotificationDBContext;
import dao.NotificationTypeDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.NotificationType;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.Email;
import websocket.endpoints.AdminDashboardWebSocketEndpoint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Register extends HttpServlet {
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
            response.sendRedirect("./verify-code");
        } else {
            request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String givenName = request.getParameter("given-name");
        String familyName = request.getParameter("family-name");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String verifyPassword = request.getParameter("verify-password");
        // Kiểm tra xem các trường có giá trị hay không
        if (givenName != null && familyName != null && userName != null && email != null && password != null && verifyPassword != null) {
            UserDBContext db = new UserDBContext();
            if (db.checkEmail(email)) {
                if (db.checkUsername(userName)) {
                    if (password.equals(verifyPassword)) {
                        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
                        String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
                        // Tạo một đối tượng User mới và thiết lập thông tin
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setGivenName(givenName);
                        newUser.setFamilyName(familyName);
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
                        User userAfterInsert = db.get(email);
                        ArrayList<User> tos = db.getAdmin("Administrator");
                        Notification notification = null;
                        try {
                            notification = createNotification(tos, userAfterInsert);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        NotificationDBContext notificationDBContext = new NotificationDBContext();
                        notificationDBContext.insert(notification);
                        AdminDashboardWebSocketEndpoint.notifyAdminsNewUserRegistered(notification);
                        String uri = request.getRequestURI();
                        session.setAttribute("uri", uri);
                        session.setAttribute("verifyType", verifyType);
                        // close connection
                        try {
                            db.closeConnection();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        response.sendRedirect("./verify-code");

                    } else {
                        // Xử lý lỗi nếu mật khẩu và xác nhận mật khẩu không khớp
                        request.setAttribute("error", "Password and confirm password do not match!");
                        //close connection
                        try {
                            db.closeConnection();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
                    }
                } else {
                    // Xử lý lỗi nếu username đã tồn tại
                    request.setAttribute("error", "Username already exists!");
                    //close connection
                    try {
                        db.closeConnection();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
                }

            } else {
                // Xử lý lỗi nếu email đã tồn tại
                request.setAttribute("error", "Email already exists!");
                //close connection
                try {
                    db.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
            }
        } else {
            // Xử lý lỗi nếu một số trường không có giá trị
            request.setAttribute("error", "Please fill out the form!");
            request.getRequestDispatcher("./view/user/authenticate/Register.jsp").forward(request, response);
        }
    }

    private Notification createNotification(ArrayList<User> tos, User from) throws SQLException {
        Notification notification = new Notification();
        NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();
        notification.setRead(false);
        NotificationType notificationType = notificationTypeDBContext.get(1);
        notification.setType(notificationType);
        notification.setTos(tos);
        notification.setFrom(from);
        notification.setContent(notificationType.getAction() + from.getEmail());
        notification.setUrl("/Quizzicle/admin/user/profile?uid=" + from.getId());
        // close connection
        notificationTypeDBContext.closeConnection();
        return notification;
    }
}
