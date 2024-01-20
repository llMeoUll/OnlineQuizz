package controller.user.authenticate;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/Login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDBContext db = new UserDBContext();
        // check if email and password is not null
        if (email != null && password != null) {
            email = email.trim();
            password = password.trim();
            // check if email is registered
            if (!db.checkEmail(email)){
                User param = new User();
                param.setEmail(email);
                param.setPassword(password);
                User loggedUser = db.get(param);
                if (loggedUser == null) {
                    request.setAttribute("error", "Email/Password is invalid!");
                    request.getRequestDispatcher("./view/Login.jsp").forward(request, response);
                } else {
                    try {
                        loggedUser.setRoles(db.getRolesAndFeatures(loggedUser.getUsername()));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("user", loggedUser);
                    response.sendRedirect("./");
                }
            }
            else {
                request.setAttribute("error", "Email is not registered!");
                request.getRequestDispatcher("./view/Login.jsp").forward(request, response);
                return;
            }

        } else {
            request.setAttribute("error", "Email/Password is invalid!");
            request.getRequestDispatcher("./view/Login.jsp").forward(request, response);
            return;
        }

    }
}
