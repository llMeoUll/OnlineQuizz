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
        int setID = Integer.parseInt(request.getParameter("setID"));
        QuestionDBContext questionDBContext = new QuestionDBContext();
        request.setAttribute("listQuestion", questionDBContext.list(setID));
        request.setAttribute("setID", setID);
        request.getRequestDispatcher("../.././view/user/set/GetSet.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  numOfStarRate = request.getParameter("numberOfStar");
        SetDBContext setDBContext = new SetDBContext();
        Set set = setDBContext.get(Integer.parseInt(request.getParameter("setId")));
        if(numOfStarRate != null) {
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
        }
        response.sendRedirect("./get?setID="+set.getSId());
    }
}
