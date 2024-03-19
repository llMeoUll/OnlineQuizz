package controller.user.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.CommentDBContext;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateLike extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new Gson().fromJson(request.getReader(), JsonObject.class);
        int comment_id = jsonObject.get("comment_id").getAsInt();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //update like and dislike
        int like = 0;
        CommentDBContext commentDBContext = new CommentDBContext();
        if(commentDBContext.checkUserDisliked(comment_id, user.getId()) == false){
            if(commentDBContext.checkUserLiked(comment_id, user.getId()) == false){
                commentDBContext.insertLike(comment_id, user.getId());
                like = 1;
            }else{
                commentDBContext.deleteLike(comment_id, user.getId());
                like = -1;
            }
        }

        // close connection
        try {
            commentDBContext.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // return response for ajax: comment id,
        JsonObject result = new JsonObject();
        result.addProperty("comment_id", comment_id);
        result.addProperty("like", like);
        response.getWriter().write(result.toString());
    }

}
