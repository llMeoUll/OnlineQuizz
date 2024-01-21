package dao;

import entity.Room;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDbContext extends DBContext<Room> {

    @Override
    public Room get(Room entity) {
        return null;
    }

    @Override
    public ArrayList<Room> list() {

        return null;
    }


    @Override
    public void insert(Room entity) {
        String sql = "INSERT INTO `online_quizz`.`room`\n" +
                "(`room_id`,\n" +
                "`room_name`,\n" +
                "`code`,\n" +
                "`password`,\n" +
                "`description`,\n" +
                "`uid`,\n" +
                "`created_at`,\n" +
                "`code_to_join`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?);\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getRoom_id());
            stm.setString(2, entity.getRoom_name());
            stm.setString(3, entity.getCode());
            stm.setString(4, entity.getPassword());
            stm.setString(5, entity.getDescription());
            stm.setInt(6, entity.getUser().getUid());
            stm.setDate(7, entity.getCreated_at());
            stm.setString(8, entity.getCode_to_join());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(Room entity) {

    }

    public ArrayList<Room> list(User user) {
        ArrayList<Room> listRoom = new ArrayList<>();
        String sql = "SELECT `room`.`room_id`,\n" +
                "    `room`.`room_name`,\n" +
                "    `room`.`code`,\n" +
                "    `room`.`password`,\n" +
                "    `room`.`description`,\n" +
                "    `room`.`uid`,\n" +
                "    `room`.`created_at`,\n" +
                "    `room`.`updated_at`\n" +
                "FROM `online_quizz`.`room`\n" +
                "WHERE uid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getUid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoom_id(rs.getInt(1));
                r.setRoom_name(rs.getString(2));
                r.setCode(rs.getString(3));
                r.setPassword(rs.getString(4));
                r.setDescription(rs.getString(5));
                r.setUser(user);
                r.setCreated_at(rs.getDate(7));
                r.setUpdated_at(rs.getDate(8));
                listRoom.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listRoom;
    }

    /**
     * Check xem room đó có tồn tại để join không
     *
     * @param code
     * @param password
     * @return null nếu không tồn tại
     */
    public Room getRoomToJoin(String code, String password) {
        String sql = "SELECT `room`.`room_id`,\n" +
                "    `room`.`room_name`,\n" +
                "    `room`.`code`,\n" +
                "    `room`.`password`,\n" +
                "    `room`.`description`,\n" +
                "    `room`.`uid`,\n" +
                "    `room`.`created_at`,\n" +
                "    `room`.`updated_at`\n" +
                "FROM `online_quizz`.`room`\n" +
                "WHERE code = ? AND password= ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, code);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoom_id(rs.getInt(1));
                r.setRoom_name(rs.getString(2));
                r.setCode(code);
                r.setPassword(password);
                r.setDescription(rs.getString(5));
                return r;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * User có 2 list room
     * 1 là list tự tạo, 2 là list do join (được sinh bởi bảng nhiều nhiều này)
     *
     * @param uid
     * @param room_id
     */
    public void insertIntoUser_Join_Room(int uid, int room_id) {
        String sql = "INSERT INTO `online_quizz`.`user_join_room`\n" +
                "(`uid`,\n" +
                "`room_id`)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?);\n";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, room_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Room> roomJoinedByUser(User user) {
        ArrayList<Room> listRoomJoinedByUser = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "SELECT user_join_room.uid, user_join_room.room_id, room.room_name, room.code, room.password, room.description, room.created_at, room.uid, user.display_name\n" +
                    "FROM online_quizz.user_join_room\n" +
                    "INNER JOIN online_quizz.room \n" +
                    "ON user_join_room.room_id = room.room_id AND user_join_room.uid = ?\n" +
                    "INNER JOIN online_quizz.user\n" +
                    "ON room.uid = user.uid";

            PreparedStatement stm = null;
            stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getUid());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoom_id(rs.getInt(2));
                r.setRoom_name(rs.getString(3));
                r.setCode(rs.getString(4));
                r.setPassword(rs.getString(5));
                r.setDescription(rs.getString(6));
                r.setCreated_at(rs.getDate(7));
                User u = new User();
                u.setUid(rs.getInt(8));
                u.setDisplayName(rs.getString(9));
                r.setUser(u);
                listRoomJoinedByUser.add(r);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listRoomJoinedByUser;
    }

    public Room getRoomToJoin(String codeToJoin) {
        String sql = "SELECT `room`.`room_id`,\n" +
                "    `room`.`room_name`,\n" +
                "    `room`.`code`,\n" +
                "    `room`.`password`,\n" +
                "    `room`.`description`,\n" +
                "    `room`.`uid`,\n" +
                "    `room`.`created_at`,\n" +
                "    `room`.`updated_at`,\n" +
                "    `room`.`code_to_join`\n" +
                "FROM `online_quizz`.`room`\n" +
                "WHERE code_to_join = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, codeToJoin);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoom_id(rs.getInt(1));
                r.setRoom_name(rs.getString(2));
                r.setCode(rs.getString("code"));
                r.setPassword(rs.getString("password"));
                r.setCode_to_join(rs.getString("code_to_join"));
                r.setDescription(rs.getString(5));
                User u = new User();
                u.setUid(rs.getInt("uid"));
                r.setUser(u);
                return r;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
