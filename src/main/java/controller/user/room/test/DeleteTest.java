package controller.user.room.test;

import dao.TestDBContext;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class DeleteTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestDBContext tDb = new TestDBContext();
        int testId = Integer.parseInt(request.getParameter("testId"));
        Test test = new Test();
        test.setTestId(testId);
         test = tDb.getTestById(test);
        tDb.deleteById(test);
        response.sendRedirect("../../room/get?roomId=" + test.getRoom().getRoomId());
    }
}
