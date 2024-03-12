package dao;

import entity.SelfTest;
import entity.SelfTestQuestion;
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
                 "`created_at`)\n" +
                 "VALUES\n" +
                 "(?,\n" +
                 "current_timestamp());";
         PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
         statement.setInt(1, selfTest.getUser().getId());
         statement.executeUpdate();
         ResultSet resultSet = statement.getGeneratedKeys();
         while (resultSet.next()) {
            int selfTestId = resultSet.getInt(1);
            selfTest.setSelfTestId(selfTestId);
            for(SelfTestQuestion selfTestQuestion : selfTestQuestions) {
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
}
