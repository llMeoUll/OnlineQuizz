package controller.user.room.test;

import dao.NotificationDBContext;
import dao.NotificationTypeDBContext;
import dao.TestDBContext;
import dao.UserDBContext;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DateTimeLocalConverter;
import websocket.endpoints.RoomWebSocketEndpoint;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReviewBeforeCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../../.././view/user/room/test/ReviewTest.jsp").forward(request, response);
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

        String[] questions = request.getParameterValues("question-ids");
        ArrayList<TestQuestion> testQuestions = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            float score = Float.parseFloat(request.getParameter("score-question-" + questions[i]));
            int questionId = Integer.parseInt(questions[i]);
            TestQuestion testQuestion = new TestQuestion();
            testQuestion.setQId(questionId);
            testQuestion.setScore(score);
            testQuestions.add(testQuestion);
        }
        // insert test to database
        TestDBContext testDBContext = new TestDBContext();
        try {
            int testId = testDBContext.insert(test, testQuestions);
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();

            Notification notification = new Notification();
            NotificationType notificationType = notificationTypeDBContext.get(10);
            UserDBContext userDBContext = new UserDBContext();
            notification.setFrom(room.getUser());
            notification.setType(notificationType);
            notification.setRead(false);
            notification.setUrl("/Quizzicle/user/room/test/get?testId=" + testId);
            notification.setContent(room.getUser().getFamilyName() + " " + room.getUser().getGivenName() + " "
                    + notificationType.getAction() + " in room: " + room.getRoomName());
            ArrayList<User> tos = userDBContext.list(room);
            notification.setTos(tos);

            RoomWebSocketEndpoint.sendMessageToOtherUsers(notification);

            notificationDBContext.insert(notification);
            session.removeAttribute("room");
            session.removeAttribute("questions");
            session.removeAttribute("test");
            response.sendRedirect("../../../room/get?roomId=" + room.getRoomId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
