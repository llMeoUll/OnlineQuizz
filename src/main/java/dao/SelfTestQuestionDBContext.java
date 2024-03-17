package dao;

import entity.SelfTestQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelfTestQuestionDBContext extends DBContext{
    public void insertAll(ArrayList<SelfTestQuestion> selfTestQuestions, Connection connection) throws SQLException {
        try {
            String sql = "INSERT INTO `online_quizz`.`self-test_question`\n" +
                    "(`self-test_id`,\n" +
                    "`question_qid`,\n" +
                    "`user_answer`)\n" +
                    "VALUES\n" +
                    "(?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            for(SelfTestQuestion selfTestQuestion : selfTestQuestions) {
                statement.setInt(1, selfTestQuestion.getSelfTest().getSelfTestId());
                statement.setInt(2, selfTestQuestion.getQuestion().getQId());
                statement.setString(3, selfTestQuestion.getAnswer());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
    }
}
