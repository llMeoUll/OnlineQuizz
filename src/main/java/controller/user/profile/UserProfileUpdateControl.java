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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import jakarta.servlet.http.Part;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@MultipartConfig
public class UserProfileUpdateControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int id = u.getId();
        UserDBContext udb = new UserDBContext();
        User user = udb.get(id);
        request.setAttribute("user", user);
        // close connection
        try {
            udb.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("uid"));

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String old_pass = request.getParameter("current_password");
        String password = request.getParameter("password");
        // upload images
//        Part file = request.getPart("avatar");
//        String imgFileName = file.getSubmittedFileName();
//        String uploadPath = "D:/session5/SWP391/OnlineQuizz/src/main/webapp/imagines/" + imgFileName;
//
//        try {
//            FileOutputStream fos = new FileOutputStream(uploadPath);
//            InputStream is = file.getInputStream();
//
//            byte[] data = new byte[is.available()];
//            is.read(data);
//            fos.write(data);
//            fos.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String avatar = request.getParameter("avatar");
        // Kiểm tra xem các trường có giá trị hay không
        if (password != null && !password.equals(old_pass)) {
            String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
            // Tạo một đối tượng User mới và thiết lập thông tin
            UserDBContext userDBContext = new UserDBContext();
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setGivenName(firstName);
            newUser.setFamilyName(lastName);
            newUser.setPassword(generatedSecuredPasswordHash);
            newUser.setUsername(userName);
            newUser.setAvatar(avatar);
            newUser.setId(id);
            Date updatedAt = new Date();
            newUser.setUpdatedAt(new Timestamp(updatedAt.getTime()));

            // xác thực và upload ảnh lên google drive
//            try{
//                GoogleCredential credential = new GoogleCredential.Builder()
//                        .setTransport(new NetHttpTransport())
//                        .setJsonFactory(new JacksonFactory())
//                        .setClientSecrets(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET)
//                        .build();
//            }
            userDBContext.updateUserProfile(newUser);

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
            // Thêm người dùng vào cơ sở dữ liệu
        } else {
            // Xử lý lỗi nếu mật khẩu và xác nhận mật khẩu không khớp
            request.setAttribute("error", "Password and confirmation password must not be the same!");
            request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);
        }
        request.getRequestDispatcher("../../view/user/profile/EditProfile.jsp").forward(request, response);
    }
}
