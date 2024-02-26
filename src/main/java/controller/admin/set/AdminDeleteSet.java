package controller.admin.set;

import dao.SetDBContext;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class AdminDeleteSet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SetDBContext setDBContext = new SetDBContext();
        int id = Integer.parseInt(request.getParameter("sid"));
        Set set = new Set();
        set.setSId(id);
        setDBContext.delete(set);
        response.sendRedirect("../set");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
