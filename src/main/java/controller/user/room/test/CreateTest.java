package controller.user.room.test;

import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DateTimeLocalConverter;

import java.io.IOException;
import java.sql.Timestamp;

public class CreateTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../.././view/user/room/test/CreateTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get room id
        // get user id
//        2000-07-09T16:47

        String Name = request.getParameter("name");
        String Description = request.getParameter("description");
        Timestamp startTime = DateTimeLocalConverter.DateTimeLocalToTimestamp(request.getParameter("start"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int attempt = Integer.parseInt(request.getParameter("attempt"));
        Timestamp endTime = new Timestamp(startTime.getTime() + duration * 60000);

        Test test = new Test();
        test.setTestName(Name);
        test.setTestDescription(Description);
        test.setDuration(duration);
        test.setAttempt(attempt);
        test.setStartTime(startTime);
        test.setEndTime(endTime);

        HttpSession session = request.getSession();
        session.setAttribute("test", test);
        response.sendRedirect("../../.././view/user/room/test/AddQuestion.jsp");

    }
}