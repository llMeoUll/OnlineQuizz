package controller.admin.question;

import dao.*;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteQuestion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromSet = request.getParameter("from-set");
        QuestionDBContext questionDBContext = new QuestionDBContext();
        int qid = Integer.parseInt(request.getParameter("qid"));
        Question deletedQuestion = questionDBContext.get(qid);
        Notification notification = new Notification();
        UserDBContext userDBContext = new UserDBContext();
        NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();
        User from = (User) request.getSession().getAttribute("user");
        ArrayList<User> tos = new ArrayList<>();
        tos.add(deletedQuestion.getSet().getUser());
        NotificationType notificationType = notificationTypeDBContext.get(8);
        notification.setFrom(from);
        notification.setTos(tos);
        notification.setType(notificationType);
        notification.setUrl("/Quizzicle/user/set/get?setID=" + deletedQuestion.getSet().getSId());
        notification.setRead(false);
        notification.setContent("Admin delete your question in set: " + deletedQuestion.getSet().getSName());
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        notificationDBContext.insert(notification);
        questionDBContext.delete(qid);
        // Close connection
        try {
            questionDBContext.closeConnection();
            userDBContext.closeConnection();
            notificationDBContext.closeConnection();
            notificationTypeDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (fromSet != null) {
            response.sendRedirect(".././set/details?setId=" + deletedQuestion.getSet().getSId());
            return;
        } else {
            response.sendRedirect("../question");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
