package controller.user.set;

import dao.SetDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class SetManage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        SetDBContext dao = new SetDBContext();
        request.setAttribute("listSet", dao.list(user));
        request.getRequestDispatcher(".././view/user/set/Manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
