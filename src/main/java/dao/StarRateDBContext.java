package dao;

import entity.Set;
import entity.StarRate;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StarRateDBContext extends DBContext {
    public StarRate get(int uid, int sid) {
        String sql = "SELECT * FROM online_quizz.star_rate\n" +
                "Where uid = ? and sid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, sid);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                StarRate starRate = new StarRate();
                starRate.setRate(resultSet.getInt("rate"));
                return starRate;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public float getAverageRate(int sid) {
        String sql = "SELECT AVG(rate) as avgRate FROM online_quizz.star_rate\n" +
                "Where sid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getFloat("avgRate");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(StarRate starRate) {
        String sqlInsertStarRate = "INSERT INTO online_quizz.`star_rate`\n" +
                "(uid,\n" +
                "sid,\n" +
                "rate,\n" +
                "created_at,\n" +
                "updated_at)\n" +
                "VALUES\n" +
                "(?,?,?,current_timestamp(), current_timestamp());";
        try {
            PreparedStatement stmInsertStarRate = connection.prepareStatement(sqlInsertStarRate);
            stmInsertStarRate.setInt(1, starRate.getUser().getId());
            stmInsertStarRate.setInt(2, starRate.getSet().getSId());
            stmInsertStarRate.setInt(3, starRate.getRate());
            stmInsertStarRate.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(StarRate starRate) {
        String sqlUpdateStarRate = "UPDATE `online_quizz`.`star_rate`\n" +
                "SET\n" +
                "`rate` = ?,\n" +
                "`updated_at` = current_timestamp()\n" +
                "WHERE `uid` = ? AND `sid` = ?;";
        try {
            PreparedStatement stmUpdateStarRate = connection.prepareStatement(sqlUpdateStarRate);
            stmUpdateStarRate.setInt(1, starRate.getRate());
            stmUpdateStarRate.setInt(2, starRate.getUser().getId());
            stmUpdateStarRate.setInt(3, starRate.getSet().getSId());
            stmUpdateStarRate.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkStarRated(StarRate starRate) {
        String sql = "SELECT * FROM online_quizz.star_rate\n" +
                "Where uid = ? and sid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, starRate.getUser().getId());
            stm.setInt(2, starRate.getSet().getSId());
            ResultSet resultSet = stm.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
