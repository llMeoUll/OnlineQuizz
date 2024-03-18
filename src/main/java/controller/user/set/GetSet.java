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
        HttpSession session = request.getSession();
        // listSet
//
        int setID = Integer.parseInt(request.getParameter("setId"));;
//        QuestionDBContext questionDBContext = new QuestionDBContext();
//        request.setAttribute("listQuestion", questionDBContext.list(setID));
        request.setAttribute("setID", setID);

        // user(avatar, name)
        // Comment(comment_id, content, reply_id, (count)likes, (count)unlikes, time)
//        User u = (User) session.getAttribute("user");
//        int id = u.getId();
//        UserDBContext udb = new UserDBContext();
//        User user = udb.get(id);

        SetDBContext sdb = new SetDBContext();
        Set set = sdb.get(setID);

        CommentDBContext cdb = new CommentDBContext();
//        // get a list comment
        ArrayList<Comment> comments = cdb.list(set);
        ArrayList<ArrayList<Comment>> replyList = new ArrayList<>();
        for (Comment c : comments) {
            replyList.add(cdb.listReplyComment(c.getCommentId()));
        }
        //comment
        session.setAttribute("setID", setID);
        request.setAttribute("replyList", replyList);
        request.setAttribute("listC", comments);
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
            notification.setUrl("/Quizzicle/user/set/get?setID=" + set.getSId());
            notification.setContent(from.getFamilyName() + " " +
                    from.getGivenName() + " " + notificationType.getAction() + " " + set.getSName());
            notificationDBContext.insert(notification);

            StarRate starRate = new StarRate();
            starRate.setRate(Integer.parseInt(numOfStarRate));
            starRate.setSet(set);
            starRate.setUser((User) session.getAttribute("user"));
            starRateDBContext.insert(starRate);
            starRateDBContext.update(starRate);
        }
        response.sendRedirect("./get?setID=" + set.getSId());
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
