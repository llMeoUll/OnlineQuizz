package controller.user.comment;

import dao.CommentDBContext;
import dao.SetDBContext;
import entity.Comment;
import entity.Set;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CommentDBContext cdb = new CommentDBContext();
        int setId = Integer.parseInt(request.getParameter("setId"));
        SetDBContext sdb = new SetDBContext();
        Set set = sdb.get(setId);

        String comment_parent = request.getParameter("comment");
        Comment comment = new Comment();
        String stringReplyId = request.getParameter("replyId");

        if (stringReplyId != null) {
            int replyId = Integer.parseInt(stringReplyId);
            Comment parentComment = new Comment();
            parentComment.setCommentId(replyId);
            comment.setParentComment(parentComment);
        }

        comment.setSet(set);
        comment.setUser(user);
        comment.setContent(comment_parent);
        cdb.insert(comment);

        // close connection
        try {
            sdb.closeConnection();
            cdb.closeConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        response.sendRedirect("../set/get?setId=" + setId);
    }
}
