package controller.admin;

import controller.admin.schedule.AutoSchedule;
import dao.ActiveUsersDBContext;
import dao.UserDBContext;
import entity.ActiveUsers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DashBoardController", value = "/dashboard")
public class DashBoardController extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        ArrayList<Integer> listNumberOfActiveUser = activeUsersDBContext.numberOfActiveUser();
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list();
        request.setAttribute("listNumberOfActiveUser", listNumberOfActiveUser);
        request.setAttribute("users", users);
        request.getRequestDispatcher("view/pages/admin/DashBoard.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
