package controller.user.roomController;

import controller.user.roomController.utilities.BasedAuthentication;
import dao.RoomDbContext;
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
        // check code
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            // thay 3 là thằng session account logged uid
            rDB.insertIntoUser_Join_Room(3, r.getRoomId());
            response.sendRedirect("/OnlineQuizz_war_exploded/ManageRoom");

        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        int ownerUserId = Integer.parseInt(request.getParameter("ownerUser"));
        String code = request.getParameter("code");
        String passwordForJoining = request.getParameter("passwordForJoining");
        RoomDbContext rDB = new RoomDbContext();
        // Check exist room
        Room r;
        r = rDB.getRoomToJoin(code, passwordForJoining);
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(ownerUserId, r.getRoomId());
            response.sendRedirect("/OnlineQuizz_war_exploded/ManageRoom");
        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }
    }
}
