package controller.user.room.test;

import dao.TestDBContext;
import entity.Test;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

public class UpdateTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("testId"));
        String testName = request.getParameter("testName");
        String testDescription = request.getParameter("testDescription");

        Test t = new Test();
        t.setTestId(testId);
        t.setTestName(testName);
        t.setTestDescription(testDescription);
//
        TestDBContext tDb = new TestDBContext();
        tDb.updateById(t);
        // user/room/get?roomId = ?
        t = tDb.getTestById(t);
        response.sendRedirect("../../room/get?roomId=" + t.getRoom().getRoomId());

    }
}
