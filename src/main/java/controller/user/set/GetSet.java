package controller.user.set;


import dao.*;
import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int setId = Integer.parseInt(request.getParameter("setId"));
        QuestionDBContext questionDBContext = new QuestionDBContext();
        CommentDBContext cdb = new CommentDBContext();
        request.setAttribute("setId", setId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        // get list Comment of a setId
        SetDBContext sdb = new SetDBContext();
        Set set = sdb.get(setId);
        // get a list comment
        ArrayList<Comment> comments = cdb.list(set);
        ArrayList<ArrayList<Comment>> replyList = new ArrayList<>();
        for (Comment c : comments) {
            replyList.add(cdb.listReplyComment(c.getCommentId()));
        }
        //get star rate
        StarRateDBContext starRateDBContext = new StarRateDBContext();
        StarRate rate = starRateDBContext.get(user.getId(), setId);
        if (rate != null) {
            request.setAttribute("rate", rate.getRate());
        } else {
            request.setAttribute("rate", 0);
        }

        // get average rate
        float avgRate = starRateDBContext.getAverageRate(setId);
        request.setAttribute("avgRate", avgRate);
        request.setAttribute("setId", setId);
        request.setAttribute("replyList", replyList);
        request.setAttribute("listC", comments);
        request.setAttribute("listQuestion", questionDBContext.list(setId));

        // close connection
        try {
            sdb.closeConnection();
            cdb.closeConnection();
            starRateDBContext.closeConnection();
            questionDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../.././view/user/set/GetSet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numOfStarRate = request.getParameter("numberOfStar");
        SetDBContext setDBContext = new SetDBContext();
        Set set = setDBContext.get(Integer.parseInt(request.getParameter("setId")));
        if (numOfStarRate != null) {
            StarRateDBContext starRateDBContext = new StarRateDBContext();
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            NotificationTypeDBContext notificationTypeDBContext = new NotificationTypeDBContext();
            HttpSession session = request.getSession();
            User from = (User) session.getAttribute("user");

            Notification notification = new Notification();
            NotificationType notificationType = notificationTypeDBContext.get(11);
            notification.setType(notificationType);
            notification.setFrom(from);
            ArrayList<User> tos = new ArrayList<>();
            tos.add(set.getUser());
            notification.setTos(tos);
            notification.setRead(false);
            notification.setUrl("/Quizzicle/user/set/get?setId=" + set.getSId());
            notification.setContent(from.getFamilyName() + " " +
                    from.getGivenName() + " " + notificationType.getAction() + " " + set.getSName());
            notificationDBContext.insert(notification);

            StarRate starRate = new StarRate();
            starRate.setRate(Integer.parseInt(numOfStarRate));
            starRate.setSet(set);
            starRate.setUser((User) session.getAttribute("user"));
            if(!starRateDBContext.checkStarRated(starRate)){
                starRateDBContext.insert(starRate);
            }
            starRateDBContext.update(starRate);
            // close connection
            try {
                starRateDBContext.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        response.sendRedirect("./get?setId=" + set.getSId());
    }


    private int getIdType(String typeName) {
        TypeDBContext typeDBContext = new TypeDBContext();
        ArrayList<Type> types = typeDBContext.list();
        for (Type type : types) {
            if (type.getTypeName().equals(typeName)) {
                return type.getTypeId();
            }
        }
        return -1;
    }
}
