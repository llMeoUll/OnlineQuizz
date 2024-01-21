package controller;

import dao.RoomDbContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utilities.GenerateCode;
import utilities.GenerateCodeToJoin;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "CreateRoomServlet", value = "/createRoom")
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
        String codeToJoin = GenerateCodeToJoin.generateCode(10);

        // Get the current date using java.time.LocalDate
        LocalDate currentDate = LocalDate.now();

        // Convert java.time.LocalDate to java.sql.Date
        Date sqlDate = Date.valueOf(currentDate);


        Room r = new Room();
        User u = new User();
        u.setUid(uid);

        r.setUser(u);
        r.setRoom_name(roomName);
        r.setCode(code);
        r.setPassword(password);
        r.setDescription(description);
        r.setCreated_at(sqlDate);
        r.setCode_to_join(codeToJoin);

        RoomDbContext rDB = new RoomDbContext();
        rDB.insert(r);

        response.sendRedirect("/OnlineQuizz/ManageRoom");
    }
}
