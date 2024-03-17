package dao;

import entity.Comment;
import entity.Role;
import entity.Set;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDBContext extends DBContext {
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
            while (rs.next()) {
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

    public ArrayList<Comment> list(Set entity) {
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
                "WHERE `comment`.`sid` = ? and `comment`.`reply_id` is null;";
        try {
            PreparedStatement stmGetComments = connection.prepareStatement(sqlGetComments);
            stmGetComments.setInt(1, entity.getSId());
            ResultSet rs = stmGetComments.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setSet(entity);
                UserDBContext udb = new UserDBContext();
                User user = udb.get(rs.getInt("uid"));
                comment.setUser(user);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("unlikes"));

                Date createdAt = rs.getTimestamp("created_at");
                Date updatedAt = rs.getTimestamp("updated_at");
                comment.setCreatedAt(createdAt);
                comment.setUpdatedAt(updatedAt);

                ArrayList<Comment> replyComments = listReplyComment(rs.getInt("comment_id"));
                comment.setRepliedComments(replyComments);
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    public ArrayList<Comment> listReplyComment(int parentCommentId) {
        ArrayList<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM `comment` c\n" +
                "where c.reply_id = ?;";
        try {
            PreparedStatement stmGetComments = connection.prepareStatement(sql);
            stmGetComments.setInt(1, parentCommentId);
            ResultSet rs = stmGetComments.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                SetDBContext sdb = new SetDBContext();
                Set set = sdb.get(rs.getInt("sid"));
                comment.setSet(set);
                UserDBContext udb = new UserDBContext();
                User user = udb.get(rs.getInt("uid"));
                comment.setUser(user);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("unlikes"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    public void insert(Comment entity) {
        String sqlInsert = "INSERT INTO `online_quizz`.`comment`\n" +
                "(`sid`,\n" +
                "`uid`,\n" +
                "`content`,\n" +
                "`likes`,\n" +
                "`unlikes`,\n" +
                "`created_at`,\n" +
                "`updated_at`,\n" +
                "`reply_id`)\n" +
                "VALUES\n" +
                "(?, ?, ?, 0, 0, current_timestamp(), current_timestamp(), ?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlInsert);
            stm.setInt(1, entity.getSet().getSId());
            stm.setInt(2, entity.getUser().getId());
            stm.setString(3, entity.getContent());
            if (entity.getParentComment() != null) {
                stm.setInt(4, entity.getParentComment().getCommentId());
            } else {
                stm.setNull(4, Types.INTEGER); // Set to null if there is no reply
            }

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int cmtId, int likes, int unlikes) {
        try {
            String sqlUpdateUser = "UPDATE `online_quizz`.`comment`\n" +
                    "SET\n" +
                    "`likes` = ?,\n" +
                    "`unlikes`= ?\n" +
                    "WHERE `comment_id` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setInt(1, cmtId);
            stmUpdateUser.setInt(2, likes);
            stmUpdateUser.setInt(3, unlikes);
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

