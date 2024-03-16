package dao;

import entity.SelfTest;
import entity.SelfTestQuestion;
import entity.Set;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelfTestDBContext extends DBContext {
    public void insert(SelfTest selfTest, ArrayList<SelfTestQuestion> selfTestQuestions) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `online_quizz`.`self-test`\n" +
                    "(`uid`,\n" +
                    "`sid`,\n" +
                    "`created_at`)\n" +
                    "VALUES\n" +
                    "(?, ?, current_timestamp());";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, selfTest.getUser().getId());
            statement.setInt(2, selfTest.getSet().getSId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                int selfTestId = resultSet.getInt(1);
                selfTest.setSelfTestId(selfTestId);
                for (SelfTestQuestion selfTestQuestion : selfTestQuestions) {
                    selfTestQuestion.setSelfTest(selfTest);
                }
                SelfTestQuestionDBContext selfTestQuestionDBContext = new SelfTestQuestionDBContext();
                selfTestQuestionDBContext.insertAll(selfTestQuestions, connection);
            }
            connection.commit();


        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<SelfTest> get(int userId, int setId) {
        ArrayList<SelfTest> selfTests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM online_quizz.`self-test`\n" +
                    "where uid = ? and sid = ? " +
                    "order by created_at desc;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, setId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SelfTest selfTest = new SelfTest();
                Set set = new Set();
                set.setSId(resultSet.getInt("sid"));
                User user = new User();
                user.setId(resultSet.getInt("uid"));
                selfTest.setSelfTestId(resultSet.getInt("self-test_id"));
                selfTest.setSet(set);
                selfTest.setUser(user);
                selfTest.setCreatedAt(resultSet.getTimestamp("created_at"));
                selfTests.add(selfTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selfTests;
    }
}
