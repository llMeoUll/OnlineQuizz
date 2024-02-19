package controller.user.room.utilities;

import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public abstract class BasedAuthentication extends HttpServlet {

    boolean isAuthenticated(HttpServletRequest request) {
        User accountLogged = (User) request.getSession().getAttribute("user");
        return accountLogged != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            doGet(request, response, (User) request.getSession().getAttribute("user"));
        } else {
            response.sendRedirect(".././login");
        }
    }

    abstract protected void doGet(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isAuthenticated(request)) {
            doPost(request, response, (User) request.getSession().getAttribute("user"));
        } else {
            response.sendRedirect(".././login");
        }
    }

    abstract protected void doPost(HttpServletRequest request, HttpServletResponse response, User userLogged) throws ServletException, IOException;
}
