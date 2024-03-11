package dao;

import entity.*;
import entity.ViewModel.LeaderBoardViewModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                t.setAttempt(rs.getInt("attempt"));
                t.setDuration(rs.getInt("duration"));
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

    public int getCurrentAttemptOfThisTest(Test currentTest, User userLogged) {
        String sql = "SELECT uid, test_id, max(attempt) as attempt \n" +
                "FROM online_quizz.user_does_test \n" +
                "GROUP BY uid, test_id \n" +
                "HAVING uid = ? AND test_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userLogged.getId());
            stm.setInt(2, currentTest.getTestId());
            ResultSet rs = stm.executeQuery();
            // != null
            if (rs.next()) {
                int currentAttemp = rs.getInt("attempt");
                return currentAttemp;
            }
            // null => 0
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertAttemptDetails(Test currentTest, User userLogged, int currentAttempt, Timestamp createdAt) {
        String sql = "INSERT INTO `online_quizz`.`user_does_test`\n" +
                "(`uid`,\n" +
                "`test_id`,\n" +
                "`attempt`,\n" +
                "`created_at`)\n" +
                "VALUES\n" +
                "(\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userLogged.getId());
            stm.setInt(2, currentTest.getTestId());
            stm.setInt(3, currentAttempt);
            stm.setTimestamp(4, createdAt);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Question, Float> getExactlyAnswerQuestionsInCertainTest(User userLogged, Test currentTest) {
        HashMap<Question, Float> getExactlyAnswerQuestionsInCertainTest = new HashMap<>();
        String sql = "SELECT question.qid,  question.question, question.answer, test_question.score FROM online_quizz.test\n" +
                "JOIN online_quizz.test_question ON test.test_id = test_question.test_test_id\n" +
                "JOIN online_quizz.question ON test_question.question_qid = question.qid\n" +
                "WHERE test.test_id = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, currentTest.getTestId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQId(rs.getInt("qid"));
                q.setQuestion(rs.getString("question"));
                q.setAnswer(rs.getString("answer"));

                Float score = new Float(rs.getFloat("score"));
                getExactlyAnswerQuestionsInCertainTest.put(q, score);
            }
            return getExactlyAnswerQuestionsInCertainTest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertAnswerOfTestWithCorrespondingScore(HashMap<Question, Float> questionSubmittedAndItsScoreAfterComparing, int udt_id) {
        try {
            connection.setAutoCommit(false);

            for (Map.Entry<Question, Float> entryExactlyAnswer : questionSubmittedAndItsScoreAfterComparing.entrySet()) {
                String sql = "INSERT INTO `online_quizz`.`user_answer`\n" +
                        "(`udt_id`,\n" +
                        "`qid`,\n" +
                        "`user_answer`,\n" +
                        "`score`)\n" +
                        "VALUES\n" +
                        "(?,\n" +
                        "?,\n" +
                        "?,\n" +
                        "?);\n";
                PreparedStatement stm = connection.prepareStatement(sql);
                Question question = entryExactlyAnswer.getKey();
                Float score = entryExactlyAnswer.getValue();
                stm.setInt(1, udt_id);
                stm.setInt(2, question.getQId());
                stm.setString(3, question.getAnswer());
                stm.setFloat(4, score);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getUserDoesTestId(User userLogged, Test currentTest, int currentAttempt) {
        String sql = "SELECT * FROM online_quizz.user_does_test\n" +
                "WHERE uid = ? AND test_id = ? AND attempt = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userLogged.getId());
            stm.setInt(2, currentTest.getTestId());
            stm.setInt(3, currentAttempt);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("udt_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public ArrayList<LeaderBoardViewModel> getLeaderBoardViewModels(Test currentTest) {
        String sql = "SELECT udt.udt_id, udt.attempt, u.username, udt.created_at, SUM(ua.score) AS score, test.test_name, test.test_description\n" +
                "FROM online_quizz.user_does_test as udt\n" +
                "JOIN test ON udt.test_id = test.test_id\n" +
                "JOIN room ON test.room_id = room.room_id\n" +
                "JOIN user_join_room as ujr ON ujr.room_id = room.room_id AND ujr.uid = udt.uid\n" +
                "JOIN user as u ON u.uid = ujr.uid\n" +
                "JOIN user_answer as ua ON ua.udt_id = udt.udt_id\n" +
                "GROUP BY udt.udt_id, udt.attempt, u.username, udt.created_at, test.test_name, test.test_description, test.test_id\n" +
                "HAVING test.test_id = ?;";
        ArrayList<LeaderBoardViewModel> leaderBoardViewModels = new ArrayList<>();

        String sql2 = "SELECT test.test_id, test.test_name, SUM(test_question.score) as total_score FROM online_quizz.test\n" +
                "JOIN online_quizz.test_question \n" +
                "ON test.test_id = test_question.test_test_id\n" +
                "GROUP BY test.test_id, test.test_name\n" +
                "HAVING test_id = ?;\n";

        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, currentTest.getTestId());
            ResultSet rs = stm.executeQuery();

            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setInt(1, currentTest.getTestId());
            ResultSet rs2 = stm2.executeQuery();

            float totalScore = 0;
            if (rs2.next()) {
                totalScore = rs2.getFloat("total_score");
            }
            while (rs.next()) {
                LeaderBoardViewModel leaderBoardViewModel = new LeaderBoardViewModel();
                leaderBoardViewModel.setTotalScore(totalScore);
                User user = new User();
                user.setUsername(rs.getString("username"));
                leaderBoardViewModel.setUser(user);

                Test test = new Test();
                test.setTestName(rs.getString("test_name"));
                test.setTestDescription(rs.getString("test_description"));
                leaderBoardViewModel.setTest(test);


                leaderBoardViewModel.setUdtId(rs.getInt("udt_id"));
                leaderBoardViewModel.setOrderAttempt(rs.getInt("attempt"));
                leaderBoardViewModel.setCreatedAt(rs.getTimestamp("created_at"));
                leaderBoardViewModel.setScore(rs.getFloat("score"));
                leaderBoardViewModels.add(leaderBoardViewModel);
            }
            return leaderBoardViewModels;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void insert(Test entity, ArrayList<TestQuestion> testQuestions) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `online_quizz`.`test`\n" +
                    "(`room_id`,\n" +
                    "`test_name`,\n" +
                    "`test_description`,\n" +
                    "`duration`,\n" +
                    "`start_time`,\n" +
                    "`end_time`,\n" +
                    "`attempt`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`)\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?,\n" +
                    "current_timestamp(),\n" +
                    "current_timestamp());";
            try {
                PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stm.setInt(1, entity.getRoom().getRoomId());
                stm.setString(2, entity.getTestName());
                stm.setString(3, entity.getTestDescription());
                stm.setInt(4, entity.getDuration());
                stm.setTimestamp(5, entity.getStartTime());
                stm.setTimestamp(6, entity.getEndTime());
                stm.setInt(7, entity.getAttempt());
                stm.executeUpdate();
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    for (TestQuestion testQuestion : testQuestions) {
                        testQuestion.setTestId(rs.getInt(1));
                    }
                    TestQuestionDBContext testQuestionDBContext = new TestQuestionDBContext();
                    testQuestionDBContext.insertAll(testQuestions, connection);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getOwnerTest(Test currentTest) {
        String sql = "SELECT room.uid FROM online_quizz.test\n" +
                "JOIN online_quizz.room ON test.room_id = room.room_id AND test.test_id = ?;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, currentTest.getTestId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("uid"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
