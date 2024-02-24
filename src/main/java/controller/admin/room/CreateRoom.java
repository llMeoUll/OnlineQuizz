package controller.admin.room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CreateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/admin/CreateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
