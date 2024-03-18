package controller.admin.set;

import dao.NotificationDBContext;
import dao.NotificationTypeDBContext;
import dao.SetDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

public class AdminDeleteSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SetDBContext setDBContext = new SetDBContext();
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        UserDBContext userDBContext = new UserDBContext();
        NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();

        int id = Integer.parseInt(request.getParameter("sid"));
        Set set = setDBContext.get(id);
        Notification notification = new Notification();
        notification.setUrl("/Quizzicle/user/set");
        User from = (User)request.getSession().getAttribute("user");
        notification.setFrom(from);
        ArrayList<User> tos = new ArrayList<>();
        tos.add(set.getUser());
        notification.setType(notificationTypeDBContext.get(3));
        notification.setTos(tos);
        notification.setRead(false);
        notification.setContent("Admin delete your set: " + set.getSName());

        notificationDBContext.insert(notification);
        setDBContext.delete(set);
        // Close connection
        try {
            setDBContext.closeConnection();
            notificationDBContext.closeConnection();
            userDBContext.closeConnection();
            notificationTypeDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../set");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
