package controller.user.authenticate;

import dao.UserDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
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
            if (!db.checkEmail(email)) {
                User loggedUser = db.get(email, password);
                if (loggedUser == null) {
                    request.setAttribute("error", "Email/Password is invalid!");
                    // close connection
                    try {
                        db.closeConnection();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", loggedUser);
                // close connection
                try {
                    db.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath() + "/home");

            } else {
                request.setAttribute("error", "Email is not registered!");
                // close connection
                try {
                    db.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
                return;
            }

        } else {
            request.setAttribute("error", "Email/Password is invalid!");
            // close connection
            try {
                db.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("./view/user/authenticate/Login.jsp").forward(request, response);
            return;
        }

    }
}
