package controller.user.roomController;

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

@WebServlet(name = "RoomServlet", value = "/ManageRoom")
public class RoomServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assume beacuse don't have Session to get User
        UserDBContext uDB = new UserDBContext();
        User u = new User();
        u.setId(3);
        u = uDB.get(u);
        RoomDbContext rDB = new RoomDbContext();
        ArrayList<Room> listRoom = rDB.list(u);
        ArrayList<Room> listRoomJoinedByUser = rDB.roomJoinedByUser(u)  ;
        request.setAttribute("userHasRoom", u);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listRoomJoinedByUser", listRoomJoinedByUser);



        request.getRequestDispatcher("RoomScreen/ManageRoomScreen.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
