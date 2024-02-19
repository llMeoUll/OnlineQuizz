package dao;

import entity.Comment;
import entity.Set;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDBContext extends DBContext{
    public ArrayList<Comment> list(User entity) {
        ArrayList<Comment> comments = new ArrayList<>();
        String sqlGetComments = "SELECT `comment`.`comment_id`,\n" +
                "    `comment`.`sid`,\n" +
                "    `comment`.`uid`,\n" +
                "    `comment`.`content`,\n" +
                "    `comment`.`likes`,\n" +
                "    `comment`.`unlikes`,\n" +
                "    `comment`.`created_at`,\n" +
                "    `comment`.`updated_at`,\n" +
                "    `comment`.`reply_id`\n" +
                "FROM `online_quizz`.`comment`\n" +
                "WHERE `comment`.`uid` = ?;";
        try {
            PreparedStatement stmGetComments = connection.prepareStatement(sqlGetComments);
            stmGetComments.setInt(1, entity.getId());
            ResultSet rs = stmGetComments.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
                comment.setSet(set);
                comment.setUser(entity);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("unlikes"));
                ArrayList<Comment> replyComments = new ArrayList<>();
                Comment replyComment = new Comment();
                replyComment.setCommentId(rs.getInt("reply_id"));
                replyComments.add(replyComment);
                comment.setRepliedComments(replyComments);
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
