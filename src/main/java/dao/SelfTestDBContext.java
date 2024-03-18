package dao;

import entity.SelfTest;
import entity.SelfTestQuestion;
import entity.Set;
import entity.User;
import entity.view.SelfTestHistory;

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
                    "`sid`," +
                    "`result`,\n" +
                    "`created_at`)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, current_timestamp());";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, selfTest.getUser().getId());
            statement.setInt(2, selfTest.getSet().getSId());
            statement.setInt(3, selfTest.getResult());
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

    public ArrayList<SelfTestHistory> list(int userId, int setId) {
        ArrayList<SelfTestHistory> selfTests = new ArrayList<>();
        try {
            String sql = "SELECT st.sid, st.uid, st.`self-test_id`, st.`result`, \n" +
                    "st.created_at, count(st.`self-test_id`) as `number-of-question` \n" +
                    "FROM online_quizz.`self-test` st\n" +
                    "inner join `self-test_question` tq on tq.`self-test_id` = st.`self-test_id`\n" +
                    "group by st.sid, st.uid, st.`self-test_id`\n" +
                    "having uid = ? and sid = ?\n" +
                    "order by st.created_at desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, setId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SelfTestHistory selfTest = new SelfTestHistory();
                Set set = new Set();
                set.setSId(resultSet.getInt("sid"));
                User user = new User();
                user.setId(resultSet.getInt("uid"));
                selfTest.setSelfTestId(resultSet.getInt("self-test_id"));
                selfTest.setSet(set);
                selfTest.setUser(user);
                selfTest.setCreatedAt(resultSet.getTimestamp("created_at"));
                selfTest.setResult(resultSet.getInt("result"));
                selfTest.setNumberOfQuestion(resultSet.getInt("number-of-question"));
                selfTests.add(selfTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selfTests;
    }

    public SelfTest get(int selfTestId) {
        SelfTest selfTest = new SelfTest();
        String sql = "SELECT * FROM `self-test` WHERE `self-test_id` = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, selfTestId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                selfTest.setSelfTestId(resultSet.getInt("self-test_id"));
                User user = new User();
                user.setId(resultSet.getInt("uid"));
                selfTest.setUser(user);
                Set set = new Set();
                set.setSId(resultSet.getInt("sid"));
                selfTest.setSet(set);
                selfTest.setResult(resultSet.getInt("result"));
                selfTest.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selfTest;
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
