package controller.user.set;

import dao.SelfTestDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class SelfTestHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int setId = Integer.parseInt(request.getParameter("setId"));
        SelfTestDBContext selfTestDBContext = new SelfTestDBContext();
        request.setAttribute("selfTests", selfTestDBContext.get(user.getId(), setId));
        request.getRequestDispatcher("../../.././view/user/set/SelfTestHistory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
