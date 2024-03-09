package controller.user.room.test;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class CancelCreateTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("test") != null){
            session.removeAttribute("test");
        }
        if (session.getAttribute("sets") != null){
            session.removeAttribute("sets");
        }
        response.sendRedirect("../../test");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
