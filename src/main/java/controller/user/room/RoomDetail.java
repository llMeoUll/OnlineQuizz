package controller.user.room;

import dao.RoomDBContext;
import dao.TestDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.Test;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

public class RoomDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Room r = new Room();
        r.setRoomId(roomId);


        User userLogged = (User) request.getSession().getAttribute("user");
        UserDBContext uDB = new UserDBContext();
        User u = uDB.get(userLogged.getEmail());

        TestDBContext tDB = new TestDBContext();
        ArrayList<Test> listTestOfRoom = tDB.getTestsCorrespondingEachRoom(u, r);
        String roomName = listTestOfRoom.get(0).getRoom().getRoomName();
        r.setRoomName(roomName);
        RoomDBContext rDB = new RoomDBContext();
        r = rDB.getRoomByName(r);

        request.setAttribute("currentRoom", r);
        request.setAttribute("listTestOfRoom", listTestOfRoom);


        request.getRequestDispatcher("../../view/user/room/ViewRoomDetail.jsp").forward(request, response);
//        response.sendRedirect("../room");

    }


}
