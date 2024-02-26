package dao;


import entity.HashTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HashtagDBContext extends DBContext{
    public void insertAll(ArrayList<HashTag> hashTags, int setId, Connection connection) throws SQLException {
        String insertHashtagQuery = "INSERT INTO `online_quizz`.`hash_tag`\n" +
                "(`tag_name`,\n" +
                "`created_at`,\n" +
                "`updated_at`)\n" +
                "VALUES\n" +
                "(?,current_timestamp(),current_timestamp());";

        try (PreparedStatement hashtagStatement = connection.prepareStatement(insertHashtagQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (HashTag hashTag : hashTags) {
                int tagId = checkHashtagExist(hashTag.getName());
                HashtagSetMappingDBContext hashtagSetMappingDBContext = new HashtagSetMappingDBContext();
                if(tagId == -1){
                    hashtagStatement.setString(1, hashTag.getName());
                    hashtagStatement.executeUpdate();
                    ResultSet generatedKeys = hashtagStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        tagId = generatedKeys.getInt(1);
                        hashtagSetMappingDBContext.insert(setId, tagId, connection);
                    } else {
                        throw new SQLException("Failed to retrieve hashtag ID.");
                    }
                } else {
                    hashtagSetMappingDBContext.insert(setId, tagId, connection);
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


    public ArrayList<HashTag> list(int setId, Connection connection) {
        String sql = "select t.tag_id, t.tag_name \n" +
                "from online_quizz.hash_tag_set_mapping ht\n" +
                "inner join online_quizz.hash_tag t on t.tag_id = ht.tag_id\n" +
                "where ht.sid = ?;";
        ArrayList<HashTag> hashTags = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                HashTag hashTag = new HashTag();
                hashTag.setId(resultSet.getInt("tag_id"));
                hashTag.setName(resultSet.getString("tag_name"));
                hashTags.add(hashTag);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hashTags;
    }


    public void deleteAll(ArrayList<HashTag> hashTags, Connection connection) throws SQLException {
        String sql = "DELETE FROM `online_quizz`.`hash_tag` WHERE `tag_id` = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            for (HashTag hashTag : hashTags) {
                stm.setInt(1, hashTag.getId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
