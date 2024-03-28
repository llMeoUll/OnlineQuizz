package controller.admin.set;

import dao.SetDBContext;
import entity.Set;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DateTimeLocalConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SetManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SetDBContext setDBContext = new SetDBContext();
        ArrayList<Set> sets = setDBContext.list();
        request.setAttribute("sets", sets);
        // Close connection
        try {
            setDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../view/admin/SetManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SetDBContext setDBContext = new SetDBContext();
        String setName = request.getParameter("name");
        String fromDateString = request.getParameter("fromDate");
        String toDateString = request.getParameter("toDate");
        Timestamp fromDate = (fromDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(fromDateString);
        Timestamp toDate = (toDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(toDateString);
        ArrayList<Set> sets = setDBContext.search(setName, fromDate, toDate);
        try {
            setDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("sets", sets);
        request.getRequestDispatcher("../view/admin/SetManagement.jsp").forward(request, response);
    }
}
