package controller;

import dao.RoomDbContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "InviteServlet", value = "/invite")
public class InviteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codeToJoin = request.getParameter("codeToJoin");
        // invite?codeToJoin=V5YNfddEg6
        RoomDbContext rDB = new RoomDbContext();
        Room r = new Room();
        r = rDB.getRoomToJoin(codeToJoin);
        // check code
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            // thay 3 là thằng session account logged uid
            rDB.insertIntoUser_Join_Room(3, r.getRoomId());
        } else {
            response.getWriter().println("404 - Not found");
        }

        response.sendRedirect("/OnlineQuizz/ManageRoom");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ownerUserId = Integer.parseInt(request.getParameter("ownerUser"));
        String code = request.getParameter("code");
        String passwordForJoining = request.getParameter("passwordForJoining");
        RoomDbContext rDB = new RoomDbContext();
        // Check exist room
        Room r = new Room();
        r = rDB.getRoomToJoin(code, passwordForJoining);
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(ownerUserId, r.getRoomId());
        } else {
            response.getWriter().println("404 - Not found");
        }

        response.sendRedirect("/OnlineQuizz/ManageRoom");
    }
}
