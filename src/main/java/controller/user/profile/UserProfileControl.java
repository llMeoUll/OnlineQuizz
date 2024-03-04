package controller.user.profile;

import dao.RoomDBContext;
import dao.SetDBContext;
import dao.UserDBContext;
import entity.Room;
import entity.Set;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int id = u.getId();

        //User
        UserDBContext udb = new UserDBContext();
        User user = udb.get(id);
        int countSet = udb.CountSet(user.getId());
        int countRoom = udb.CountRoom(user.getId());
        // Set
        SetDBContext sdb = new SetDBContext();
        List<Set> listS = sdb.getOwnedSet(user);
        // Room
        RoomDBContext rdb = new RoomDBContext();
        List<Room> listR = rdb.list(user);

        request.setAttribute("user",user);
        request.setAttribute("countSet",countSet);
        request.setAttribute("countRoom",countRoom);
        request.setAttribute("listS",listS);
        request.setAttribute("listR",listR);
        request.getRequestDispatcher("./view/user/Profile/UserProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
