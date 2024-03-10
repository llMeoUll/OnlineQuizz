package controller.user.room.test;

import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class CancelCreateTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Room room = (Room) session.getAttribute("room");
        if(session.getAttribute("test") != null){
            session.removeAttribute("test");
        }
        if (session.getAttribute("sets") != null){
            session.removeAttribute("sets");
        }
        if(session.getAttribute("questions") != null){
            session.removeAttribute("questions");
        }
        if(session.getAttribute("room") != null){
            session.removeAttribute("room");
        }
        response.sendRedirect("../../.././room/get?roomId=" + room.getRoomId());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
