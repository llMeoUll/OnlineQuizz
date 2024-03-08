package controller.admin.user;

import dao.UserDBContext;
import entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/admin/CreateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String givenName = request.getParameter("given_name");
        String familyName = request.getParameter("family_name");
        String password = request.getParameter("password");
        UserDBContext userDBContext = new UserDBContext();
        if(userDBContext.checkEmail(email) && userDBContext.checkUsername(username)) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setGivenName(givenName);
            newUser.setFamilyName(familyName);
            newUser.setPassword(password);
            Date createdAt = new Date();
            newUser.setCreatedAt(new Timestamp(createdAt.getTime()));
            Role role = new Role();
            role.setRId(2);
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            newUser.setRoles(roles);
            userDBContext.insert(newUser);
            response.sendRedirect("../user");
        }
        else {

        }
    }
}
