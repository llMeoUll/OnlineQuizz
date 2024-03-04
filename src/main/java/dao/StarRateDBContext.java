package dao;

import entity.Set;
import entity.StarRate;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StarRateDBContext extends DBContext {
    public ArrayList<StarRate> list(User entity) {
        ArrayList<StarRate> ratedStars = new ArrayList<>();
        String sqlGetRatedStar = "SELECT `star_rate`.`uid`,\n" +
                "    `star_rate`.`sid`,\n" +
                "    `star_rate`.`rate`,\n" +
                "    `star_rate`.`created_at`,\n" +
                "    `star_rate`.`updated_at`\n" +
                "FROM `online_quizz`.`star_rate`\n" +
                "WHERE `star_rate`.`uid` = ?";
        try {
            PreparedStatement stmGetRatedStar = connection.prepareStatement(sqlGetRatedStar);
            stmGetRatedStar.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetRatedStar.executeQuery();
            while(rs.next()) {
                StarRate starRate = new StarRate();
                starRate.setUser(entity);
                starRate.setRate(Integer.parseInt(rs.getString("rate")));
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
                starRate.setSet(set);
                ratedStars.add(starRate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratedStars;
    }
}
