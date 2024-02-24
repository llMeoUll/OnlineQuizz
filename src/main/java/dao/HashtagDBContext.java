package dao;


import entity.HashTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HashtagDBContext extends DBContext{
    public void insertHashtags(ArrayList<HashTag> hashTags, int setId, Connection connection) throws SQLException {
        String insertHashtagQuery = "INSERT INTO `online_quizz`.`hash_tag`\n" +
                "(`tag_name`,\n" +
                "`created_at`,\n" +
                "`updated_at`)\n" +
                "VALUES\n" +
                "(?,current_timestamp(),current_timestamp());";

        try (PreparedStatement hashtagStatement = connection.prepareStatement(insertHashtagQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (HashTag hashTag : hashTags) {
                int tagId = checkHashtagExist(hashTag.getName());
                if(tagId == -1){
                    hashtagStatement.setString(1, hashTag.getName());
                    hashtagStatement.executeUpdate();
                    ResultSet generatedKeys = hashtagStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        tagId = generatedKeys.getInt(1);
                        insertHashtagSetMapping(setId, tagId, connection);
                    } else {
                        throw new SQLException("Failed to retrieve hashtag ID.");
                    }
                } else {
                    insertHashtagSetMapping(setId, tagId, connection);
                }

            }
        } catch (SQLException e) {
            // Rollback the transaction if an exception occurs
            connection.rollback();
            throw new RuntimeException("Transaction failed.", e);
        }
    }

    //check hashtag exist, if exist return hashtag id, if not return -1
    public int checkHashtagExist(String hashtag) {
        String sql = "SELECT `tag_id` FROM `online_quizz`.`hash_tag` WHERE `tag_name` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, hashtag);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("tag_id"); // Return the ID if the hashtag exists
            } else {
                return -1; // Return -1 if the hashtag doesn't exist
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //insert hashtag set mapping
    public void insertHashtagSetMapping(int setId, int tagId, Connection connection) throws SQLException {
        String sql = "INSERT INTO `online_quizz`.`hash_tag_set_mapping` (`sid`, `tag_id`) VALUES (?, ?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            stm.setInt(2, tagId);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Rollback the transaction if an exception occurs
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
