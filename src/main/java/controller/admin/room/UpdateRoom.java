package controller.admin.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class UpdateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Room room = new Room();
        room.setRoomId(Integer.parseInt(request.getParameter("room_id")));
        RoomDBContext roomDBContext = new RoomDBContext();
        Room receivedRoom = roomDBContext.getRoomById(room);
        request.setAttribute("room", receivedRoom);
        request.getRequestDispatcher("../../view/admin/UpdateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomName = request.getParameter("roomName");
        String description = request.getParameter("description");
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomName(roomName);
        Date updatedAtDate = new Date();
        Timestamp timestamp = new Timestamp(updatedAtDate.getTime());
        room.setUpdatedAt(timestamp);
        room.setDescription(description);
        RoomDBContext roomDBContext = new RoomDBContext();
        roomDBContext.updateRoom(room);
        response.sendRedirect("../room");
    }
}
