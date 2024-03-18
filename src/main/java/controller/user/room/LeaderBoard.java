package controller.user.room;

import dao.TestDBContext;
import entity.Test;
import entity.view.LeaderBoardViewModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

public class LeaderBoard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("testId"));
        Test currentTest = new Test();
        currentTest.setTestId(testId);
        TestDBContext testDBContext = new TestDBContext();
        ArrayList<LeaderBoardViewModel> leaderBoardViewModels = testDBContext.getLeaderBoardViewModels(currentTest);
        // close connection
        try {
            testDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("leaderBoardViewModels", leaderBoardViewModels);
        request.getRequestDispatcher("../../../view/user/room/LeaderBoard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
