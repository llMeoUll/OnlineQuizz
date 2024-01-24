package controller.user.roomController;

import controller.user.roomController.utilities.BasedAuthentication;
import dao.RoomDbContext;
import dao.UserDBContext;
import entity.Room;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller này dùng để quản lý room management screen. Tức là khi người dùng phần room thì họ sẽ thấy được cái list
 * room của mình. (Tự tạo hoặc đã joined ở room khác)
 */

public class RoomServlet extends BasedAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException {
        // User này lấy từ session nên chỉ có mỗi email và password
        UserDBContext uDB = new UserDBContext();
        User u;
        u = uDB.getUserByEmail(userLogged); // get full information based on email
        RoomDbContext rDB = new RoomDbContext();
        ArrayList<Room> listRoom = rDB.list(u);
        ArrayList<Room> listRoomJoinedByUser = rDB.roomJoinedByUser(u);
        request.setAttribute("userHasRoom", u);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listRoomJoinedByUser", listRoomJoinedByUser);
        request.getRequestDispatcher("/view/user/RoomScreen/ManageRoomScreen.jsp").forward(request, response);
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
