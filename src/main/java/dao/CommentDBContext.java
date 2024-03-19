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
    public ArrayList<Comment> list(Set entity) {
        ArrayList<Comment> comments = new ArrayList<>();
        String sqlGetComments = "SELECT c.comment_id, c.sid, c.uid, c.content,\n" +
                "c.created_at, c.updated_at, c.reply_id, count(distinct l.uid) as likes,\n" +
                "count(distinct d.uid) as dislikes\n" +
                "FROM online_quizz.comment c\n" +
                "left join user_like_comment l on l.comment_id = c.comment_id\n" +
                "left join user_dislike_comment d on d.comment_id = c.comment_id\n" +
                "group by c.comment_id, c.sid, c.uid, c.content,\n" +
                "c.created_at, c.updated_at\n" +
                "having c.sid = ? and c.reply_id is null";
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
                udb.closeConnection();
                comment.setUser(user);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("dislikes"));

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
        String sql = "SELECT c.comment_id, c.sid, c.uid, c.content,\n" +
                "c.created_at, c.updated_at, c.reply_id, count(distinct l.uid) as likes,\n" +
                "count(distinct d.uid) as dislikes\n" +
                "FROM online_quizz.comment c\n" +
                "left join user_like_comment l on l.comment_id = c.comment_id\n" +
                "left join user_dislike_comment d on d.comment_id = c.comment_id\n" +
                "group by c.comment_id, c.sid, c.uid, c.content,\n" +
                "c.created_at, c.updated_at\n" +
                "having c.reply_id = ?";
        try {
            PreparedStatement stmGetComments = connection.prepareStatement(sql);
            stmGetComments.setInt(1, parentCommentId);
            ResultSet rs = stmGetComments.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                SetDBContext sdb = new SetDBContext();
                Set set = sdb.get(rs.getInt("sid"));
                sdb.closeConnection();
                comment.setSet(set);
                UserDBContext udb = new UserDBContext();
                User user = udb.get(rs.getInt("uid"));
                udb.closeConnection();
                comment.setUser(user);
                comment.setContent(rs.getString("content"));
                comment.setLikes(rs.getInt("likes"));
                comment.setUnlikes(rs.getInt("dislikes"));
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
                "`created_at`,\n" +
                "`updated_at`,\n" +
                "`reply_id`)\n" +
                "VALUES\n" +
                "(?,?,?,\n" +
                "current_timestamp(),\n" +
                "current_timestamp(),?);";
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

    public void insertLike(int cmtId, int userId) {
        try {
            String sqlUpdateUser = "INSERT INTO `online_quizz`.`user_like_comment`\n" +
                    "(`comment_id`,\n" +
                    "`uid`)\n" +
                    "VALUES\n" +
                    "(?, ?);";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setInt(1, cmtId);
            stmUpdateUser.setInt(2, userId);
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteLike(int cmtId, int userId) {
        try {
            String sqlUpdateUser = "DELETE FROM `online_quizz`.`user_like_comment`\n" +
                    "WHERE `comment_id` = ? and `uid` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setInt(1, cmtId);
            stmUpdateUser.setInt(2, userId);
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertDislike(int cmtId, int userId) {
        try {
            String sqlUpdateUser = "INSERT INTO `online_quizz`.`user_dislike_comment`\n" +
                    "(`comment_id`,\n" +
                    "`uid`)\n" +
                    "VALUES\n" +
                    "(?, ?);\n";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setInt(1, cmtId);
            stmUpdateUser.setInt(2, userId);
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDislike(int cmtId, int userId) {
        try {
            String sqlUpdateUser = "DELETE FROM `online_quizz`.`user_dislike_comment`\n" +
                    "WHERE `comment_id` = ? and `uid` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setInt(1, cmtId);
            stmUpdateUser.setInt(2, userId);
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserLiked(int cmtId, int userId) {
        String sql = "SELECT * FROM online_quizz.user_like_comment where comment_id = ? and uid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cmtId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUserDisliked(int cmtId, int userId) {
        String sql = "SELECT * FROM online_quizz.user_dislike_comment where comment_id = ? and uid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cmtId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}

