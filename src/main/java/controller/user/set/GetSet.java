package controller.user.set;


import dao.QuestionDBContext;
import dao.SetDBContext;
import dao.StarRateDBContext;
import dao.TypeDBContext;
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
        ArrayList<Question> questions = questionDBContext.list(setID);
        request.setAttribute("listQuestion", questions);
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
            StarRate starRate = new StarRate();
            starRate.setRate(Integer.parseInt(numOfStarRate));
            starRate.setSet(set);
            HttpSession session = request.getSession();
            starRate.setUser((User) session.getAttribute("user"));
            starRateDBContext.insert(starRate);
            starRateDBContext.update(starRate);
        }
        response.sendRedirect("./get?setID="+set.getSId());
    }
}
