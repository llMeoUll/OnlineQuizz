package controller.user.room;

import dao.RoomDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.GenerateCode;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class CreateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("ownerUser"));
        String roomName = request.getParameter("roomName");
        String password = request.getParameter("password");
        String description = request.getParameter("description");
        String code = GenerateCode.generateCode();

        Date createdAtDate = new Date();

        Timestamp createdAt = new Timestamp(createdAtDate.getTime());

        Room r = new Room();
        User u = new User();
        u.setId(uid);

        r.setUser(u);
        r.setRoomName(roomName);
        r.setCode(code);
        r.setPassword(password);
        r.setDescription(description);
        r.setCreatedAt(createdAt);

        RoomDBContext rDB = new RoomDBContext();
        rDB.insert(r);

        response.sendRedirect("../../../Quizzicle/user/room");
    }
}
