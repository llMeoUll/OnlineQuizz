package controller.user.set;

import dao.SetDBContext;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ViewAllSet", value = "/user/set/viewAll")
public class ViewAllSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int setID = Integer.parseInt(request.getParameter("setID"));
        User u = (User) request.getSession().getAttribute("user");
        SetDBContext dao = new SetDBContext();
        request.setAttribute("listSet", dao.getSetByUserID(u.getId()));
        request.getRequestDispatcher("../.././view/user/set/ViewSet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
