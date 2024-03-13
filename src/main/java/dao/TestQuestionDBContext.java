package dao;

import entity.TestQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestQuestionDBContext extends DBContext{
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
}
