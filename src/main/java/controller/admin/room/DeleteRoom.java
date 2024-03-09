package controller.admin.room;

import dao.NotificationDBContext;
import dao.NotificationTypeDBContext;
import dao.RoomDBContext;
import dao.UserDBContext;
import entity.Notification;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import entity.User;

public class DeleteRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        RoomDBContext roomDBContext = new RoomDBContext();
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        UserDBContext userDBContext = new UserDBContext();
        NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();

        Room room = new Room();
        room.setRoomId(roomId);
        Room roomToDelete = roomDBContext.getRoomById(room);

        Notification notification = new Notification();
        notification.setRead(false);
        notification.setFrom(userDBContext.getAdmin("Admin"));
        ArrayList<User> tos = new ArrayList<>();
        tos.add(roomToDelete.getUser());
        notification.setTos(tos);
        notification.setType(notificationTypeDBContext.get(7));
        notification.setUrl("/Quizzicle/user/room");

        notificationDBContext.insert(notification);
        roomDBContext.deleteRoom(room);
        response.sendRedirect("../room");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
