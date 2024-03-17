package controller.user.set;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class SelfTestHistory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selfTestId = request.getParameter("selfTestId");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
