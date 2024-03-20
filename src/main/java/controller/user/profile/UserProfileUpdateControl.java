package controller.user.profile;


import com.lambdaworks.crypto.SCryptUtil;
import dao.NotificationDBContext;
import dao.NotificationTypeDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.NotificationType;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class UserProfileUpdateControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int id = u.getId();
        UserDBContext userDBContext = new UserDBContext();
        User newUser = new User();

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
//        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        // Get current password
        String currentPassword = request.getParameter("current-password");
        String generatedSecuredCurrentPassword = SCryptUtil.scrypt(currentPassword, 16, 16, 16);
        if (generatedSecuredCurrentPassword != u.getPassword()) {
            String wrongCurrentPassword = "Wrong old password";
            request.setAttribute("wrongCurrentPassword", wrongCurrentPassword);
            request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);
            return;
        }
        String newpassword = request.getParameter("password");
        String avatar = request.getParameter("avatarUrl").trim();
        // upload images

        // Kiểm tra xem các trường có giá trị hay không

        String generatedSecuredPasswordHash = SCryptUtil.scrypt(newpassword, 16, 16, 16);
        // Tạo một đối tượng User mới và thiết lập thông tin
        newUser.setEmail(email);
        newUser.setGivenName(firstName);
        newUser.setFamilyName(lastName);
        newUser.setPassword(generatedSecuredPasswordHash);
//        newUser.setUsername(userName);
//        newUser.setAvatar(avatar);
        newUser.setId(id);
        Date updatedAt = new Date();
        newUser.setUpdatedAt(new Timestamp(updatedAt.getTime()));
        userDBContext.updateUserProfile(newUser);

        // notification
        NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        ArrayList<User> tos = userDBContext.getAdmin("Administrator");

        Notification notification = new Notification();

        NotificationType notificationType = notificationTypeDBContext.get(9);

        notification.setContent(newUser.getEmail() + " " + notificationType.getAction());
        notification.setRead(false);
        notification.setType(notificationType);
        notification.setTos(tos);
        notification.setUrl("/Quizzicle/admin/user/profile?uid=" + newUser.getId());
        notification.setFrom(newUser);

        notificationDBContext.insert(notification);
        // close connection
        try {
            userDBContext.closeConnection();
            notificationTypeDBContext.closeConnection();
            notificationDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("user", newUser);
        request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);
    }
}
