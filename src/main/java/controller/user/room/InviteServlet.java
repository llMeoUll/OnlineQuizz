package controller.user.room;

import controller.user.room.utilities.GenerateCodeToJoin;
import dao.RoomDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InviteServlet extends HttpServlet {
    private static Map<Room, String> roomCodes = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext uDB = new UserDBContext();
        User u;
        User userLogged = (User) request.getSession().getAttribute("user");

        u = uDB.get(userLogged.getEmail());
        String fakeCode = request.getParameter("codeToJoin");
        // invite?codeToJoin=1231231231231123
        RoomDBContext rDB = new RoomDBContext();
        ArrayList<Room> listRoom = rDB.listAllRoomExceptOwner(u);
        for (Room room : listRoom) {
            String hashCode = GenerateCodeToJoin.generateCode(room.getCode() + room.getPassword());
            roomCodes.put(room, hashCode);
        }
        Room r = findRoomByFakeCode(fakeCode, roomCodes);
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(u.getId(), r.getRoomId());
            response.sendRedirect("../../../Quizzicle/user/room");
        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext uDB = new UserDBContext();
        User u;
        User userLogged = (User) request.getSession().getAttribute("user");
        u = uDB.get(userLogged.getEmail());
        String code = request.getParameter("code");
        String passwordForJoining = request.getParameter("passwordForJoining");
        RoomDBContext rDB = new RoomDBContext();
        // Check exist room
        Room r;
        r = rDB.getRoomToJoin(code, passwordForJoining);
        if (r != null) {
            // insert to Many-Many table (uid, roomid) = (ownerUserId, r.room_id)
            rDB.insertIntoUser_Join_Room(u.getId(), r.getRoomId());
            response.sendRedirect("../../../Quizzicle/user/room");
        } else {
            request.getRequestDispatcher("/view/user/RoomScreen/NotFound.jsp").forward(request, response);
        }
    }

    private static Room findRoomByFakeCode(String fakeCode, Map<Room, String> roomCodes) {
        for (Map.Entry<Room, String> entry : roomCodes.entrySet()) {
            if (entry.getValue().equals(fakeCode)) {
                return entry.getKey();
            }
        }
        return null; // No room found for the given fakeCode
    }
}
