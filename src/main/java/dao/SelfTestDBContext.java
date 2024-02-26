package dao;

import entity.SelfTest;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelfTestDBContext extends DBContext {
    public ArrayList<SelfTest> getSelfTests(User entity) {
        ArrayList<SelfTest> selfTests = new ArrayList<>();
        String sqlGetSelfTests = "SELECT `self-test`.`self-test_id`,\n" +
                "    `self-test`.`uid`,\n" +
                "    `self-test`.`num_of_ques`,\n" +
                "    `self-test`.`created_at`\n" +
                "FROM `online_quizz`.`self-test`\n" +
                "WHERE `self-test`.`uid` = ?";
        try {
            PreparedStatement stmGetSelfTests = connection.prepareStatement(sqlGetSelfTests);
            stmGetSelfTests.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetSelfTests.executeQuery();
            while (rs.next()) {
                SelfTest selfTest = new SelfTest();
                selfTest.setUser(entity);
                selfTest.setSelfTestId(rs.getInt("self-test_id"));
                selfTest.setNumbOfQues(rs.getInt("num_of_ques"));
                selfTests.add(selfTest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selfTests;
    }
}
