package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HashtagSetMappingDBContext extends DBContext{
    //insert hashtag set mapping
    public void insert(int setId, int tagId, Connection connection) throws SQLException {
        String sql = "INSERT INTO `online_quizz`.`hash_tag_set_mapping`\n" +
                "(`tag_id`,\n" +
                "`sid`)" +
                " VALUES (?, ?);";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tagId);

            stm.setInt(2, setId);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Rollback the transaction if an exception occurs
            connection.rollback();
            throw new RuntimeException(e);
        }
    }


    public void deleteAll(int sId, Connection connection) throws SQLException {
        String sql = "DELETE FROM `online_quizz`.`hash_tag_set_mapping` WHERE `sid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sId);
            stm.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
