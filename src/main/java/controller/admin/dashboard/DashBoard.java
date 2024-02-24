package controller.admin.dashboard;

import dao.ActiveUsersDBContext;
import dao.RoomDBContext;
import dao.UserDBContext;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

public class DashBoard extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        UserDBContext userDBContext = new UserDBContext();
        RoomDBContext roomDBContext = new RoomDBContext();
        ArrayList<Integer> listNumberOfActiveUser = activeUsersDBContext.numberOfActiveUser();
        ArrayList<User> newUsersInWeek = userDBContext.getNewUserInWeek();
        ArrayList<Room> roomsAndOwners = roomDBContext.listRoomAndOwner();
        request.setAttribute("newUsersInWeek", newUsersInWeek);
        request.setAttribute("listNumberOfActiveUser", listNumberOfActiveUser);
        request.setAttribute("roomsAndOwners", roomsAndOwners);
        request.getRequestDispatcher("../view/admin/DashBoard.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
