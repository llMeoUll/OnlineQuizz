package controller.admin.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        RoomDBContext roomDBContext = new RoomDBContext();
        Room room = new Room();
        room.setRoomId(roomId);
        roomDBContext.deleteRoom(room);
        response.sendRedirect("../room");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
