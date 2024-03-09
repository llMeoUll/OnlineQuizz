package controller.admin.dashboard;

import dao.ActiveUsersDBContext;
import dao.NotificationDBContext;
import dao.RoomDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

public class DashBoard extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        UserDBContext userDBContext = new UserDBContext();
        RoomDBContext roomDBContext = new RoomDBContext();
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        ArrayList<Integer> listNumberOfActiveUser = activeUsersDBContext.numberOfActiveUser();
        ArrayList<User> newUsersInWeek = userDBContext.getNewUserInWeek();
        ArrayList<Room> rooms = roomDBContext.listRoomAndOwner();
        ArrayList<Notification> notifications = notificationDBContext.list(admin);
        request.setAttribute("notifications", notifications);
        request.setAttribute("rooms", rooms);
        request.setAttribute("newUsersInWeek", newUsersInWeek);
        request.setAttribute("listNumberOfActiveUser", listNumberOfActiveUser);
        request.getRequestDispatcher("../view/admin/DashBoard.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
