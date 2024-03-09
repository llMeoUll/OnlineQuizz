package controller.user.set;


import dao.QuestionDBContext;
import dao.SetDBContext;
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
        request.setAttribute("listQuestion", questionDBContext.list(setID));
        request.setAttribute("setID", setID);
        request.getRequestDispatcher("../.././view/user/set/GetSet.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
