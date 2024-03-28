package controller.admin.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DateTimeLocalConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        RoomDBContext roomDBContext = new RoomDBContext();
        String roomName = request.getParameter("roomName");
        String fromDateString = request.getParameter("fromDate");
        String toDateString = request.getParameter("toDate");
        Timestamp fromDate = (fromDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(fromDateString);
        Timestamp toDate = (toDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(toDateString);
        ArrayList<Room> rooms = roomDBContext.search(roomName, fromDate, toDate);
        try {
            roomDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("../view/admin/RoomManagement.jsp").forward(request, response);
    }
}
