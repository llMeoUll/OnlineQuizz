package controller.admin.user;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import entity.User;
import util.DateTimeLocalConverter;

public class UserManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        ArrayList<User> users = userDBContext.list();
        request.setAttribute("users", users);
        // Close connection
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../view/admin/UserManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        String email = request.getParameter("email");
        String fromDateString = request.getParameter("fromDate");
        String toDateString = request.getParameter("toDate");
        String verificationString = request.getParameter("verification");
        Timestamp fromDate = (fromDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(fromDateString);
        Timestamp toDate = (toDateString.equals("")) ? null : DateTimeLocalConverter.DateTimeLocalToTimestamp(toDateString);
        boolean isVerified = verificationString.equalsIgnoreCase("verified");
        ArrayList<User> users = userDBContext.search(fromDate, toDate, email, isVerified);
        try {
            userDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("../view/admin/UserManagement.jsp").forward(request, response);
    }
}
