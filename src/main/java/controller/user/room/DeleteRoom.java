package controller.user.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class DeleteRoom extends HttpServlet {
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
        RoomDBContext rDb = new RoomDBContext();
        Room r = new Room();
        r.setRoomId(roomId);
        rDb.deleteRoom(r);

        // close connection
        try {
            rDb.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("../room");

    }
}
