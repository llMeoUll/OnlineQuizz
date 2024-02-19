package controller.admin.authentication;

import dao.RoleDBConext;
import dao.UserDBContext;
import entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

public class AdminLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/admin/AdminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        String email = request.getParameter("admin");
        String password = request.getParameter("adminPassword");
        User loggedUser = userDBContext.get(email, password);
        if(loggedUser != null) {
            try {
                RoleDBConext roleDBConext = new RoleDBConext();
                loggedUser.setRoles(roleDBConext.list(loggedUser.getUsername()));
                HttpSession session = request.getSession();
                session.setAttribute("user", loggedUser);
                response.sendRedirect("./admin/dashboard");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            request.setAttribute("error", "Incorrect email or password!!!");
            request.getRequestDispatcher("./view/admin/AdminLogin.jsp").forward(request, response);

        }
    }
}
