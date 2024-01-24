package controller.admin.dashboard;

import dao.ActiveUsersDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

public class DashBoardController extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        ArrayList<Integer> listNumberOfActiveUser = activeUsersDBContext.numberOfActiveUser();
        request.setAttribute("listNumberOfActiveUser", listNumberOfActiveUser);
        request.getRequestDispatcher("../view/admin/DashBoard.jsp").forward(request,response);
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
