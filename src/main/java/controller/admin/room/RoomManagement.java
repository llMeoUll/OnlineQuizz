package controller.admin.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDBContext roomDBContext = new RoomDBContext();
        ArrayList<Room> rooms = roomDBContext.listRoomAndOwner();
        request.setAttribute("rooms", rooms);
        // Close connection
        try {
            roomDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher(".././view/admin/RoomManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
