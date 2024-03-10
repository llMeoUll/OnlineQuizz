package controller.admin.set;

import dao.SetDBContext;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class AdminViewSetDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int setId = Integer.parseInt(request.getParameter("setId"));
        SetDBContext setDBContext = new SetDBContext();
        Set set = setDBContext.get(setId);
        request.setAttribute("set", set);
        request.getRequestDispatcher("../.././view/admin/SetDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
