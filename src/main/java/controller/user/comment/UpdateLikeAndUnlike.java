package controller.user.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.CommentDBContext;
import dao.SetDBContext;
import entity.Set;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateLikeAndUnlike extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        JsonObject jsonObject = new Gson().fromJson(request.getReader(), JsonObject.class);
        int likes = jsonObject.get("likes").getAsInt();
        int unlikes = jsonObject.get("unlikes").getAsInt();
        int comment_id = jsonObject.get("comment_id").getAsInt();

        //update like and dislike
        CommentDBContext cdb = new CommentDBContext();
        cdb.insert(comment_id, likes, unlikes);
        //int setId = Integer.parseInt(request.getParameter("setID"));// thieu setid
//        int setId = (int) session.getAttribute("setID");
//        SetDBContext sdb = new SetDBContext();
//        Set set = sdb.get(setId);
        // close connection
        try {
            cdb.closeConnection();
//            sdb.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("../.././view/user/set/GetSet.jsp").forward(request, response);

    }

}
