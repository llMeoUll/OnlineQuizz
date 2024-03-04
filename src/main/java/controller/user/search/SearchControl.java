package controller.user.search;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("./view/user/SearchPage/Search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
//      String type = request.getParameter("type") != null ? request.getParameter("type") : "all";

//        User
        UserDBContext udb = new UserDBContext();
        ArrayList<User> listUser = udb.search(query);
        ArrayList<Integer> countSet = new ArrayList<>();
        ArrayList<Integer> countRoom = new ArrayList<>();
        //countSet
        for (User user : listUser) {
            int counter = udb.CountSet(user.getId());
            countSet.add(counter);
        }
        //countRoom
        for (User user : listUser) {
            int counter = udb.CountRoom(user.getId());
            countRoom.add(counter);
        }
//        set
        SetDBContext sdb = new SetDBContext();
        ArrayList<Set> listSet = sdb.search(query);
//        room
        RoomDBContext rdb = new RoomDBContext();
        ArrayList<Room> listRoom = rdb.search(query);

        request.setAttribute("listUser", listUser);
        request.setAttribute("countSet", countSet);
        request.setAttribute("countRoom", countRoom);

        request.setAttribute("listSet", listSet);

        request.setAttribute("listRoom", listRoom);

        request.setAttribute("txtSearch",query);
        request.getRequestDispatcher("./view/user/SearchPage/SearchResult.jsp").forward(request, response);
    }
}
