package controller.user.home;

import dao.NotificationDBContext;
import entity.Notification;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            ArrayList<Notification> notifications = notificationDBContext.list(user);
            session.setAttribute("notifications", notifications);
        }
        request.getRequestDispatcher("./view/user/homepage/Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
