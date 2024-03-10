package dao;

import entity.TestQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestQuestionDBContext extends DBContext {
    public void insertAll(ArrayList<TestQuestion> testQuestions, Connection connection) throws SQLException {
        try {
            String query = "INSERT INTO `online_quizz`.`test_question`\n" +
                    "(`test_id`,\n" +
                    "`qid`,\n" +
                    "`score`)\n" +
                    "VALUES\n" +
                    "(?, ?, ?);";
            PreparedStatement stm = connection.prepareStatement(query);
            for (TestQuestion testQuestion : testQuestions) {
                stm.setInt(1, testQuestion.getTestId());
                stm.setInt(2, testQuestion.getQId());
                stm.setFloat(3, testQuestion.getScore());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public ArrayList<TestQuestion> list(int testId) {
        ArrayList<TestQuestion> testQuestions = new ArrayList<>();
        try {
            String query = "SELECT * FROM online_quizz.test_question WHERE test_id = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, testId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setTestId(resultSet.getInt("test_id"));
                testQuestion.setQId(resultSet.getInt("qid"));
                testQuestion.setScore(resultSet.getFloat("score"));
                testQuestions.add(testQuestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testQuestions;
    }

    public void delete(int testId, Connection connection) throws SQLException {
        try {
            String query = "DELETE FROM online_quizz.test_question WHERE test_id = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, testId);
            stm.executeUpdate();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
    }
}
