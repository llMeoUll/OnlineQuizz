package controller.user.home;

import dao.NotificationDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            ArrayList<Notification> notifications = notificationDBContext.list(user);
            // close connection
            try {
                notificationDBContext.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("notifications", notifications);
        }
        UserDBContext userDBContext = new UserDBContext();

        request.getRequestDispatcher("./Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
