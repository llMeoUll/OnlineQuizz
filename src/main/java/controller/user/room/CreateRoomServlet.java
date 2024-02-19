package controller.user.room;

import dao.RoomDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controller.user.room.utilities.GenerateCode;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class CreateRoomServlet extends HttpServlet {
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

        // Get the current date using java.time.LocalDate
        LocalDate currentDate = LocalDate.now();

        // Convert java.time.LocalDate to java.sql.Date
        Date sqlDate = Date.valueOf(currentDate);


        Room r = new Room();
        User u = new User();
        u.setId(uid);

        r.setUser(u);
        r.setRoomName(roomName);
        r.setCode(code);
        r.setPassword(password);
        r.setDescription(description);
        r.setCreatedAt(sqlDate);

        RoomDBContext rDB = new RoomDBContext();
        rDB.insert(r);

        response.sendRedirect("/Quizzicle/user/room");
    }
}
