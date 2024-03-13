package controller.user.room.test;

import dao.RoomDBContext;
import entity.Room;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DateTimeLocalConverter;

import java.io.IOException;
import java.sql.Timestamp;

public class CreateTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Room roomSession = (Room) session.getAttribute("room");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        // Create only one test at a time
        if(roomSession != null && roomSession.getRoomId() != roomId){
            session.removeAttribute("room");
            session.removeAttribute("test");
            session.removeAttribute("questions");
        }
        RoomDBContext roomDBContext = new RoomDBContext();
        Room room = new Room();
        room.setRoomId(roomId);
        room = roomDBContext.getRoomById(room);

        session.setAttribute("room", room);
        request.getRequestDispatcher("../../.././view/user/room/test/CreateTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Name = request.getParameter("name");
        String Description = request.getParameter("description");
        Timestamp startTime = DateTimeLocalConverter.DateTimeLocalToTimestamp(request.getParameter("start"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int attempt = Integer.parseInt(request.getParameter("attempt"));
        Timestamp endTime = DateTimeLocalConverter.DateTimeLocalToTimestamp(request.getParameter("end"));
        Test test = new Test();
        test.setTestName(Name);
        test.setTestDescription(Description);
        test.setDuration(duration);
        test.setAttempt(attempt);
        test.setStartTime(startTime);
        test.setEndTime(endTime);
        HttpSession session = request.getSession();
        Room room = (Room) session.getAttribute("room");
        test.setRoom(room);
        if(session.getAttribute("test") != null){
            session.removeAttribute("test");
        }
        session.setAttribute("test", test);
        response.sendRedirect("create/add-question");
    }
}
