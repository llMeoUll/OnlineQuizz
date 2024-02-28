package controller.admin.set;

import dao.SetDBContext;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class SetManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SetDBContext setDBContext = new SetDBContext();
        ArrayList<Set> sets = setDBContext.list();
        request.setAttribute("sets", sets);
        request.getRequestDispatcher("../view/admin/SetManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
