package controller.admin.authentication;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;

public class AdminLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/admin/AdminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        String email = request.getParameter("admin");
        String password = request.getParameter("adminPassword");
        User param = new User();
        param.setUsername(email);
        param.setPassword(password);
        User loggedUser = userDBContext.getByUsernameAndPassword(param);
        if(loggedUser != null) {
            try {
                loggedUser.setRoles(userDBContext.getRolesAndFeatures(loggedUser.getUsername()));
                response.sendRedirect("./admin/dashboard");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            request.setAttribute("error", "Incorrect email or password!!!");
        }
    }
}
