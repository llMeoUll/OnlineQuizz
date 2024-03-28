package controller.user.room;

import dao.TestDBContext;
import entity.Test;
import entity.view.LeaderBoardViewModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("testId"));
        TestDBContext testDBContext = new TestDBContext();

        Test currentTest = new Test();
        currentTest.setTestId(testId);
        currentTest = testDBContext.getTestById(currentTest);
        ArrayList<LeaderBoardViewModel> leaderBoardViewModels = testDBContext.getLeaderBoardViewModels(currentTest);
        // close connection
        try {
            testDBContext.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // sort leader board
        Collections.sort(leaderBoardViewModels, (o1, o2) -> {
            if (o1.getScore() == o2.getScore()) {// if score is equal then sort by order attempt
                if (o1.getOrderAttempt() == o2.getOrderAttempt()) {// if order attempt is equal then sort by created at
                    // attempt is sorted in ascending order
                    return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                } else {
                    // attempt is sorted in ascending order
                    return Integer.compare(o1.getOrderAttempt(), o2.getOrderAttempt());
                }
            } else {
                // score is sorted in descending order
                return Float.compare(o2.getScore(), o1.getScore());
            }
        });
        request.setAttribute("test", currentTest);
        request.setAttribute("leaderBoardViewModels", leaderBoardViewModels);
        request.getRequestDispatcher("../../../view/user/room/LeaderBoard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
