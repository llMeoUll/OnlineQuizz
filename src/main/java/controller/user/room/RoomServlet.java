package controller.user.room;

import controller.user.room.utilities.BasedAuthentication;
import controller.user.room.utilities.GenerateCodeToJoin;
import dao.RoomDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * Controller này dùng để quản lý room management screen. Tức là khi người dùng phần room thì họ sẽ thấy được cái list
 * room của mình. (Tự tạo hoặc đã joined ở room khác)
 */

public class RoomServlet extends BasedAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        // User này lấy từ session nên chỉ có mỗi email và password
        UserDBContext uDB = new UserDBContext();
        User u;
        u = uDB.get(userLogged.getEmail()); // get full information based on email
        request.setAttribute("userHasRoom", u);
        RoomDBContext rDB = new RoomDBContext();
        ArrayList<Room> listRoomOwned = rDB.list(u);
        ArrayList<Room> listRoomJoinedByUser = rDB.roomJoinedByUser(u);
        // Test print code room
        Map<Room, String> roomCodes = new HashMap<>();
        ArrayList<Room> listRoom = rDB.listAllRoomExceptOwner(u);
        for (Room room : listRoom) {
            String hashCode = GenerateCodeToJoin.generateCode(room.getCode() + room.getPassword());
            roomCodes.put(room, hashCode);
        }
        request.setAttribute("roomCodes", roomCodes);
        // finish test
        String selectedButton = request.getParameter("selectedButton");

        if (selectedButton == null) {
            ArrayList<Room> mergeList = new ArrayList<>();
            mergeList.addAll(listRoomOwned);
            mergeList.addAll(listRoomJoinedByUser);
            request.setAttribute("listRoom", mergeList);
        } else if (selectedButton.equals("owned")) {
            // Option: onwed
            request.setAttribute("listRoom", listRoomOwned);
        } else if (selectedButton.equals("joined")) {
            // Option: Joined
            request.setAttribute("listRoom", listRoomJoinedByUser);
        } else if (selectedButton.equals("newest")) {
            ArrayList<Room> mergeList = new ArrayList<>();
            mergeList.addAll(listRoomOwned);
            mergeList.addAll(listRoomJoinedByUser);
            // Option: Sort by date created newest
            ArrayList<Room> sortedByNewest = mergeList;
            Collections.sort(sortedByNewest, Comparator.comparing(Room::getCreatedAt));
            request.setAttribute("listRoom", sortedByNewest);
        } else if (selectedButton.equals("oldest")) {
            ArrayList<Room> mergeList = new ArrayList<>();
            mergeList.addAll(listRoomOwned);
            mergeList.addAll(listRoomJoinedByUser);
            // Option: Sort by date created older
            ArrayList<Room> sortedByOldest = mergeList;
            Collections.sort(sortedByOldest, Comparator.comparing(Room::getCreatedAt, Collections.reverseOrder()));
            request.setAttribute("listRoom", sortedByOldest);
        }

        ArrayList<String> listRoomName = rDB.listRoomName();
        request.setAttribute("listRoomName", listRoomName);

        request.getRequestDispatcher(".././view/user/room/ManageRoomScreen.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        processRequest(request, response, userLogged);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        processRequest(request, response, userLogged);
    }
}
