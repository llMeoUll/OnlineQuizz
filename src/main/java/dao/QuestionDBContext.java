package dao;

import entity.Question;
import entity.QuestionOption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDBContext extends DBContext {
    public void insertQuestions(ArrayList<Question> questions, int setId, Connection connection) throws SQLException {
            String insertQuestionQuery = "INSERT INTO `online_quizz`.`question`\n" +
                    "(`question`,\n" +
                    "`answer`,\n" +
                    "`sid`,\n" +
                    "`type_id`)\n" +
                    "VALUES (?, ?, ?, ?);";
            String insertOptionQuery = "INSERT INTO `online_quizz`.`question_opt`\n" +
                    "(`qid`,\n" +
                    "`opt_content`)\n" +
                    "VALUES (?, ?);";

            try (PreparedStatement questionStatement = connection.prepareStatement(insertQuestionQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement optionStatement = connection.prepareStatement(insertOptionQuery)) {

                for (Question question : questions) {
                    // Insert question
                    questionStatement.setString(1, question.getQuestion());
                    questionStatement.setString(2, question.getAnswer());
                    questionStatement.setInt(3, setId);
                    questionStatement.setInt(4, question.getType().getTypeId());
                    questionStatement.executeUpdate();

                    // Retrieve the generated question ID
                    ResultSet generatedKeys = questionStatement.getGeneratedKeys();
                    int questionId;
                    if (generatedKeys.next()) {
                        questionId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve question ID.");
                    }

                    // Insert options if it's a multiple-choice question
                    if (question.getType().getTypeName().equals("Multiple choice")) {
                        for (QuestionOption option : question.getQuestionOptions()) {
                            optionStatement.setInt(1, questionId);
                            optionStatement.setString(2, option.getOptContent());
                            optionStatement.addBatch();
                        }
                        optionStatement.executeBatch();
                    }
                }
            } catch (SQLException e) {
                // Rollback the transaction if an exception occurs
                connection.rollback();
                throw new RuntimeException("Transaction failed.", e);
            }
    }
}
