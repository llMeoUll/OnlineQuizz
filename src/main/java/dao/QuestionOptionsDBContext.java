package dao;

import entity.QuestionOption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionOptionsDBContext extends DBContext{
    public ArrayList<QuestionOption> list(int qId, Connection connection) {
        String sql = "SELECT * FROM `online_quizz`.`question_opt` WHERE `question_opt`.`qid` = ?";
        ArrayList<QuestionOption> questionOptions = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                QuestionOption questionOption = new QuestionOption();
                questionOption.setOptId(resultSet.getInt("opt_id"));
                questionOption.setOptContent(resultSet.getString("opt_content"));
                questionOptions.add(questionOption);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return questionOptions;
    }

    public void deleteAll(int qId, Connection connection) throws SQLException {
        String sql = "DELETE FROM `online_quizz`.`question_opt` WHERE `question_opt`.`qid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qId);
            stm.executeUpdate();
        } catch (Exception e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
