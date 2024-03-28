package controller.user.room;

import util.GenerateCodeToJoin;
import dao.RoomDBContext;
import dao.TestDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.Test;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

// user/room/get?roomId = ?
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

        RoomDBContext rDB = new RoomDBContext();

        User userLogged = (User) request.getSession().getAttribute("user");


        TestDBContext tDB = new TestDBContext();
        ArrayList<Test> listTestOfRoom = tDB.getTestsCorrespondingEachRoom(userLogged, r);
        r = rDB.getRoomById(r);
        String codeToJoin = GenerateCodeToJoin.generateCode(r.getCode() + r.getPassword());
        // close connection
        try {
            rDB.closeConnection();
            tDB.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("codeToJoin", codeToJoin);
        request.setAttribute("currentRoom", r);
        request.setAttribute("listTestOfRoom", listTestOfRoom);
        request.getRequestDispatcher("../../view/user/room/ViewRoomDetail.jsp").forward(request, response);
    }
}
