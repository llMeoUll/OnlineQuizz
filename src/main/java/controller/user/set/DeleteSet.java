package controller.user.set;


import dao.SetDBContext;
import entity.Set;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;


public class DeleteSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int setId = Integer.parseInt(request.getParameter("setId"));
        SetDBContext setDB = new SetDBContext();
        //check user is owner of set
        if (setDB.isOwner(user.getId(), setId)) {
            Set entity = new Set();
            entity.setSId(setId);
            setDB.delete(entity);
            response.sendRedirect(request.getContextPath() + "/user/set");
        } else {
            response.getWriter().println("You are not owner of this set");
        }
        // close connection
        try {
            setDB.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
