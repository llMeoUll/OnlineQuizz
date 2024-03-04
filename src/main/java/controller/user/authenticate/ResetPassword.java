package controller.user.authenticate;

import com.lambdaworks.crypto.SCryptUtil;
import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/authenticate/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();
        String password = request.getParameter("password");
        String verifyPassword = request.getParameter("verify-password");
        if(email != null && password != null && verifyPassword != null){
            if (password.equals(verifyPassword)) {
                // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
                UserDBContext db = new UserDBContext();
                String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
                db.updatePassword(email, generatedSecuredPasswordHash);
                User user = db.get(email, password);
                session.setAttribute("user", user);
                response.sendRedirect("./login");
            } else {
                request.setAttribute("error", "Mật khẩu không khớp");
                request.getRequestDispatcher("./view/user/authenticate/ResetPassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
            request.getRequestDispatcher("./view/user/authenticate/ResetPassword.jsp").forward(request, response);
        }

    }
}
