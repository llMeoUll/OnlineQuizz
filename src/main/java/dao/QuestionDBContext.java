package dao;

import entity.Question;
import entity.QuestionOption;
import entity.Set;
import entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Type;
public class QuestionDBContext extends DBContext{
    public ArrayList<Question> list(int setId) {
        ArrayList<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM `online_quizz`.`question` WHERE `question`.`sid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Question question = new Question();
                question.setQId(rs.getInt("qid"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                question.setType(new TypeDBContext().get(rs.getInt("type_id"), connection));
                if (question.getType().getTypeName().equals("Multiple choice")){
                    question.setQuestionOptions(new QuestionOptionsDBContext().list(question.getQId(), connection));
                }
                questions.add(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return questions;
    }
    public void insertAll(ArrayList<Question> questions, int setId, Connection connection) throws SQLException {
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

    public void deleteAll(int sId, Connection connection) throws SQLException {
        QuestionOptionsDBContext questionOptionsDBContext = new QuestionOptionsDBContext();
        ArrayList<Question> questions = list(sId);
        for (Question question : questions) {
            if (question.getType().getTypeName().equals("Multiple choice")) {
                questionOptionsDBContext.deleteAll(question.getQId(), connection);
            }
        }

        String sql = "DELETE FROM `online_quizz`.`question` WHERE `sid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sId);
            stm.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Question> list() {
        ArrayList<Question> questions = new ArrayList<>();
        String sqlListQuestion = "SELECT q.qid, q.question, q.answer, q.sid,\n" +
                " q.type_id, t.type_name, s.sname FROM online_quizz.question q\n" +
                " INNER JOIN online_quizz.`type` t ON t.type_id = q.type_id\n" +
                " INNER JOIN online_quizz.`set` s ON s.sid = q.sid;";
        try {
            PreparedStatement stmListQuestion = connection.prepareStatement(sqlListQuestion);
            ResultSet rs = stmListQuestion.executeQuery();
            while(rs.next()) {
                Question question = new Question();
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
                set.setSName(rs.getString("sname"));
                question.setSet(set);
                question.setQId(rs.getInt("qid"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                Type type = new Type();
                type.setTypeId(rs.getInt("type_id"));
                type.setTypeName(rs.getString("type_name"));
                question.setType(type);
                questions.add(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    public void delete(int qid) {
        String sqlDeleteQuestion = "DELETE FROM `online_quizz`.`question` WHERE `qid` = ?;";
        try {
            PreparedStatement stmDeleteQuestion = connection.prepareStatement(sqlDeleteQuestion);
            stmDeleteQuestion.setInt(1, qid);
            stmDeleteQuestion.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Question get(int qid) {
        String sqlGetQuestion = "SELECT * FROM online_quizz.question\n" +
                "WHERE `question`.`qid` = ?";
        try {
            PreparedStatement stmGetQuestion = connection.prepareStatement(sqlGetQuestion);
            stmGetQuestion.setInt(1, qid);
            ResultSet rs = stmGetQuestion.executeQuery();
            while(rs.next()) {
                Question question = new Question();
                question.setQId(qid);
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                SetDBContext setDBContext = new SetDBContext();
                Set set = setDBContext.get(rs.getInt("sid"));
                question.setSet(set);
                Type type = new Type();
                type.setTypeId(rs.getInt("type_id"));
                question.setType(type);
                return question;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
