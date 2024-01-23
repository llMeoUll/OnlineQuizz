package controller.admin.authentication;

import dao.AdminDBContext;
import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.User;
import java.io.IOException;

@WebServlet(name = "AdminLoginController", value = "/adminlogin")
public class AdminLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/pages/admin/AdminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDBContext adminDBContext = new AdminDBContext();
        String username = request.getParameter("admin");
        String password = request.getParameter("adminPassword");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User loggingUser = adminDBContext.get(user);
        if(loggingUser == null) {
            response.getWriter().println("Incorrect password");
        }
        else {
            response.sendRedirect("/OnlineQuizz/dashboard");
        }
    }
}
