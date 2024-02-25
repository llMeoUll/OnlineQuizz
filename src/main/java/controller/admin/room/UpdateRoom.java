package controller.admin.room;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class UpdateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../../view/admin/UpdateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
