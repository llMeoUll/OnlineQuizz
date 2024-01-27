package controller.user.roomController;

import controller.user.roomController.utilities.BasedAuthentication;
import dao.RoomDbContext;
import dao.UserDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class InviteServlet extends BasedAuthentication {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        String codeToJoin = request.getParameter("codeToJoin");
        // invite?codeToJoin=V5YNfddEg6
        RoomDbContext rDB = new RoomDbContext();
        Room r;
        r = rDB.getRoomToJoin(codeToJoin);
        UserDBContext uDB = new UserDBContext();
        User u;
        u = uDB.getUserByEmail(userLogged);
        // check code
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(u.getId(), r.getRoomId());
            response.sendRedirect("/OnlineQuizz_war_exploded/ManageRoom");

        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        UserDBContext uDB = new UserDBContext();
        User u;
        u = uDB.getUserByEmail(userLogged);
        String code = request.getParameter("code");
        String passwordForJoining = request.getParameter("passwordForJoining");
        RoomDbContext rDB = new RoomDbContext();
        // Check exist room
        Room r;
        r = rDB.getRoomToJoin(code, passwordForJoining);
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(u.getId(), r.getRoomId());
            response.sendRedirect("/OnlineQuizz_war_exploded/ManageRoom");
        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }
    }
}
