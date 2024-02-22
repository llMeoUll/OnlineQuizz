package dao;

import entity.Room;
import entity.Test;
import entity.User;

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
        String sql = "SELECT * FROM online_quizz.room \n" +
                "JOIN online_quizz.test WHERE room.room_id = test.room_id AND room.uid = ? AND room.room_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            stm.setInt(2, room.getRoomId());
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
                // .. Need question in each test

                t.setStartTime(rs.getTimestamp("start_time"));
                t.setEndTime(rs.getTimestamp("end_time"));
                getTestsCorrespondingEachRoom.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getTestsCorrespondingEachRoom;
    }
}
