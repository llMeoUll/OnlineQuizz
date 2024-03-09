package controller.user.room.test;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ReviewBeforeCreate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();        ;
        String[] listquest = (String[]) session.getAttribute("questions");
       for (String s : listquest) {
        response.getWriter().println(s);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
