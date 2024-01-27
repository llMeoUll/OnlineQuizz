package controller.user.set;

import dao.SetDBContext;
import entity.Set;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class CreateSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../.././view/user/set/CreateSet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        boolean isPrivate = request.getParameter("privacy").compareTo("private") == 1 ? true : false;
//        ArrayList Hastag
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        SetDBContext context = new SetDBContext();

        entity.Set set = new Set();
        set.setSName(name);
        set.setDescription(description);
        set.setPrivate(isPrivate);
        set.setUser(user);
//        context.create(set);

    }
}
