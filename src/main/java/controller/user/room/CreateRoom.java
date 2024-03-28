package controller.user.room;

import dao.RoomDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.GenerateCode;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CreateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/user/room/CreateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomName = request.getParameter("roomName");
        String password = request.getParameter("password");
        String description = request.getParameter("description");
        String code = GenerateCode.generateCode();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Date createdAtDate = new Date();
        Timestamp createdAt = new Timestamp(createdAtDate.getTime());

        Room r = new Room();
        r.setUser(user);
        r.setRoomName(roomName);
        r.setCode(code);
        r.setPassword(password);
        r.setDescription(description);
        r.setCreatedAt(createdAt);

        RoomDBContext rDB = new RoomDBContext();
        rDB.insert(r);

        // close connection
        try {
            rDB.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/user/room");
    }
}
