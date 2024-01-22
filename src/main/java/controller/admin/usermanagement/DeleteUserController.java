package controller.admin.usermanagement;

import dao.UserDBContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.User;

import java.io.IOException;

@WebServlet(name = "DeleteUserController", value = "/delete_user")
public class DeleteUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDBContext userDBContext = new UserDBContext();
        int uid = Integer.parseInt(request.getParameter("uid"));
        User user = new User();
        user.setId(uid);
        user.setRoles(userDBContext.getRoles(user));
        user.setRatedStar(userDBContext.getRatedStars(user));
        user.setDoneTest(userDBContext.getDoneTest(user));
        user.setOwnedSets(userDBContext.getOwnedSet(user));
        user.setSelfTests(userDBContext.getSelfTests(user));
        user.setOwnedRooms(userDBContext.getOwnedRoom(user));
        user.setJoinedRooms(userDBContext.getJoinedRooms(user));
        userDBContext.delete(user);
        response.sendRedirect("user_management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
