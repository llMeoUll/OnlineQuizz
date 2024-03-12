package controller.user.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.util.ArrayList;

public class CommentControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int setID = 21;
        SetDBContext sdb = new SetDBContext();
        Set set = sdb.get(setID);

        CommentDBContext cdb = new CommentDBContext();
//        // get a list comment
        ArrayList<Comment> comments = cdb.list(set);
        ArrayList<ArrayList<Comment>> replyList = new ArrayList<>();
        for (Comment c : comments) {
            replyList.add(cdb.listReplyComment(c.getCommentId()));
        }
        // setAttribute
//      request.setAttribute("user", user);
        session.setAttribute("setID", setID);
        request.setAttribute("replyList", replyList);
        request.setAttribute("listC", comments);
        request.getRequestDispatcher("../.././view/user/set/Comment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CommentDBContext cdb = new CommentDBContext();
//        int setId = Integer.parseInt(request.getParameter("setID"));// thieu setid
        int setId = (int) session.getAttribute("setID");
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

        // get a list comment
        ArrayList<Comment> comments = cdb.list(set);
        ArrayList<ArrayList<Comment>> replyList = new ArrayList<>();
        for (Comment c : comments) {
            replyList.add(cdb.listReplyComment(c.getCommentId()));
        }
        // setAttribute
        request.setAttribute("setID", setId);
        request.setAttribute("replyList", replyList);
        request.setAttribute("listC", comments);
        request.getRequestDispatcher("../.././view/user/set/Comment.jsp").forward(request, response);

    }
}
