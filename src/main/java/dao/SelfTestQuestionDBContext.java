package dao;

import entity.Question;
import entity.SelfTest;
import entity.SelfTestQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelfTestQuestionDBContext extends DBContext{
    public void insertAll(ArrayList<SelfTestQuestion> selfTestQuestions, Connection connection) throws SQLException {
        try {
            String sql = "INSERT INTO `online_quizz`.`self-test_question`\n" +
                    "(`self-test_id`,\n" +
                    "`qid`,\n" +
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

    public ArrayList<SelfTestQuestion> list(int selfTestId) {
        ArrayList<SelfTestQuestion> selfTestQuestions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM online_quizz.`self-test_question`\n" +
                    "WHERE `self-test_id` = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, selfTestId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SelfTestQuestion selfTestQuestion = new SelfTestQuestion();
                SelfTest selfTest = new SelfTest();
                selfTest.setSelfTestId(selfTestId);
                selfTestQuestion.setSelfTest(selfTest);
                Question question = new Question();
                question.setQId(resultSet.getInt("qid"));
                selfTestQuestion.setQuestion(question);
                selfTestQuestion.setAnswer(resultSet.getString("user_answer"));
                selfTestQuestions.add(selfTestQuestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selfTestQuestions;
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
