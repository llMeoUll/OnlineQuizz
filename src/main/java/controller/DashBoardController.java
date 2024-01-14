package controller;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DashBoardController", value = "/dashboard")
public class DashBoardController extends HttpServlet {
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list();
        request.setAttribute("users", users);
        request.getRequestDispatcher("view/DashBoard.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
