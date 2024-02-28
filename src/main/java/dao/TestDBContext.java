package dao;

import entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDBContext extends DBContext {
    public ArrayList<Test> getDoneTest(User entity) {
        ArrayList<Test> doneTests = new ArrayList<>();
        String sqlGetDoneTests = "SELECT `user_does_test`.`test_id`\n" +
                "FROM `online_quizz`.`user_does_test`\n" +
                "WHERE `user_does_test`.`uid` = ?";
        try {
            PreparedStatement stmGetDoneTests = connection.prepareStatement(sqlGetDoneTests);
            stmGetDoneTests.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetDoneTests.executeQuery();
            while (rs.next()) {
                Test doneTest = new Test();
                doneTest.setTestId(rs.getInt("test_id"));
                doneTests.add(doneTest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doneTests;
    }

    public ArrayList<Test> getTestsCorrespondingEachRoom(User entity, Room room) {
        ArrayList<Test> getTestsCorrespondingEachRoom = new ArrayList<>();
        String sql = "SELECT CombinedResults.uid, CombinedResults.room_id, CombinedResults.room_name, test.*\n" +
                "FROM (\n" +
                "    SELECT user_join_room.uid, user_join_room.room_id, room.room_name \n" +
                "    FROM online_quizz.user_join_room\n" +
                "    LEFT JOIN online_quizz.room ON user_join_room.room_id = room.room_id \n" +
                "    WHERE user_join_room.uid = ?\n" +
                "\n" +
                "    UNION\n" +
                "\n" +
                "    SELECT uid, room_id, room_name \n" +
                "    FROM online_quizz.room \n" +
                "    WHERE uid = ?\n" +
                ") AS CombinedResults\n" +
                "\n" +
                "JOIN online_quizz.test ON CombinedResults.room_id = test.room_id AND CombinedResults.room_id = ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            stm.setInt(2, entity.getId());
            stm.setInt(3, room.getRoomId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Test t = new Test();
                Room r = new Room();
                r.setRoomId(rs.getInt("room_id"));
                r.setRoomName(rs.getString("room_name"));
                t.setRoom(r);
                t.setTestId(rs.getInt("test_id"));
                t.setTestName(rs.getString("test_name"));
                t.setTestDescription(rs.getString("test_description"));
                // Need question in each test

                t.setStartTime(rs.getTimestamp("start_time"));
                t.setEndTime(rs.getTimestamp("end_time"));
                getTestsCorrespondingEachRoom.add(t);
            }
            return getTestsCorrespondingEachRoom;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Test getTestById(Test entity) {
        String sql = "SELECT * FROM online_quizz.test WHERE test_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getTestId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Test t = new Test();
                t.setTestId(rs.getInt("test_id"));
                Room r = new Room();
                r.setRoomId(rs.getInt("room_id"));
                t.setRoom(r);
                t.setTestName(rs.getString("test_name"));
                t.setTestDescription(rs.getString("test_description"));
                t.setStartTime(rs.getTimestamp("start_time"));
                t.setEndTime(rs.getTimestamp("end_time"));
                return t;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public ArrayList<Question> getListQuestionsOfTest(Test currentTest) {
        ArrayList<Question> listQuestions = new ArrayList<>();
        String sql = "SELECT \n" +
                "    test.test_id,\n" +
                "    test.room_id,\n" +
                "    test.test_name,\n" +
                "    test.test_description,\n" +
                "    test.start_time,\n" +
                "    test.end_time,\n" +
                "    question.qid,\n" +
                "    question.question,\n" +
                "    question.answer,\n" +
                "    question.sid,\n" +
                "    question.type_id\n" +
                "FROM online_quizz.test\n" +
                "JOIN online_quizz.test_question ON test.test_id = test_question.test_test_id\n" +
                "JOIN online_quizz.question ON test_question.question_qid = question.qid\n" +
                "WHERE test.test_id = ?;\n";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, currentTest.getTestId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQId(rs.getInt("qid"));
                q.setQuestion(rs.getString("question"));
                q.setAnswer(rs.getString("answer"));

                Set s = new Set();
                s.setSId(rs.getInt("sid"));
                q.setSet(s);

                Type t = new Type();
                t.setTypeId(rs.getInt("type_id"));
                q.setType(t);

                ArrayList<QuestionOption> listOptions = new ArrayList<>();
                int qId = rs.getInt("qid");
                String sqlListOptions = "SELECT * FROM online_quizz.question_opt WHERE qid = ?;";
                try (PreparedStatement stm2 = connection.prepareStatement(sqlListOptions)) {
                    stm2.setInt(1, qId);

                    ResultSet rs2 = stm2.executeQuery();
                    while (rs2.next()) {
                        QuestionOption questionOption = new QuestionOption();
                        questionOption.setOptId(rs2.getInt("opt_id"));
                        Question question = new Question();
                        question.setQId(rs2.getInt("qid"));
                        questionOption.setQuestion(question);
                        questionOption.setOptContent(rs2.getString("opt_content"));
                        listOptions.add(questionOption);
                    }
                }

                q.setQuestionOptions(listOptions);
                listQuestions.add(q);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listQuestions;
    }

    public void updateById(Test entity) {
        String sql = "UPDATE `online_quizz`.`test`\n" +
                "SET\n" +
                "`test_name` = ?,\n" +
                "`test_description` = ?\n" +
                "WHERE `test_id` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getTestName());
            stm.setString(2, entity.getTestDescription());
            stm.setInt(3, entity.getTestId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Test entity) {
        String sql = "DELETE FROM `online_quizz`.`test`\n" +
                "WHERE test_id = ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getTestId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
