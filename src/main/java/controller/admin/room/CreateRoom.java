package controller.admin.room;

import dao.RoomDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import entity.User;
import util.GenerateCode;

public class CreateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/admin/CreateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomName = request.getParameter("roomName");
        String description = request.getParameter("description");
        String password = request.getParameter("password");
        String code = GenerateCode.generateCode();
        Room room = new Room();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        room.setUser(user);
        room.setRoomName(roomName);
        room.setDescription(description);
        room.setPassword(password);
        room.setCode(code);
        Date createdAtDate = new Date();
        Timestamp createdAt = new Timestamp(createdAtDate.getTime());
        room.setCreatedAt(createdAt);
        RoomDBContext roomDBContext = new RoomDBContext();
        roomDBContext.insert(room);
        response.sendRedirect("../room");
    }
}
