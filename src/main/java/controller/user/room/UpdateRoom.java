package controller.user.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

public class UpdateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String roomName = request.getParameter("roomName");
        String description = request.getParameter("description");
        // Get the current date as java.util.Date
        java.util.Date utilDate = new java.util.Date();

        // Convert java.util.Date to timestamp (in milliseconds)
        long timestamp = utilDate.getTime();

        // Convert timestamp to java.sql.Timestamp
        Timestamp updatedAt = new Timestamp(timestamp);
        Room r = new Room();
        r.setRoomId(roomId);
        r.setRoomName(roomName);
        r.setDescription(description);
        r.setUpdatedAt(updatedAt);

        RoomDBContext rDB = new RoomDBContext();
        rDB.updateRoom(r);

        response.sendRedirect("../room");
    }
}
