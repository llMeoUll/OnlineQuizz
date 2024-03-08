package controller.user.profile;

import com.lambdaworks.crypto.SCryptUtil;
import dao.RoomDBContext;
import dao.SetDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.Set;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Email;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserProfileUpdateControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int id = u.getId();
        UserDBContext udb = new UserDBContext();
        User user = udb.get(id);

        request.setAttribute("user", user);
        request.getRequestDispatcher("./view/user/Profile/EditProfile.jsp").forward(request, response);
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
        String avatar = request.getParameter("avatar");
        UserDBContext db = new UserDBContext();
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
            userDBContext.updateUserProfile(newUser);
            session.setAttribute("user", newUser);
            // Thêm người dùng vào cơ sở dữ liệu
        } else {
            // Xử lý lỗi nếu mật khẩu và xác nhận mật khẩu không khớp
            request.setAttribute("error", "Password and confirmation password must not be the same!");
            request.getRequestDispatcher("./view/user/Profile/EditProfile.jsp").forward(request, response);
        }
        request.getRequestDispatcher("./view/user/Profile/EditProfile.jsp").forward(request, response);
    }
}
