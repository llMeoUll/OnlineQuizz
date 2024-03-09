package dao;

import entity.Room;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDBContext extends DBContext {

    public void insert(Room entity) {
        String sql = "INSERT INTO `online_quizz`.`room`\n" + "(`room_id`,\n" + "`room_name`,\n" + "`code`,\n" + "`password`,\n" + "`description`,\n" + "`uid`,\n" + "`created_at`)\n" + "VALUES\n" + "(?, ?, ?, ?, ?, ?, ?);\n";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, entity.getRoomId());
            stm.setString(2, entity.getRoomName());
            stm.setString(3, entity.getCode());
            stm.setString(4, entity.getPassword());
            stm.setString(5, entity.getDescription());
            stm.setInt(6, entity.getUser().getId());
            stm.setTimestamp(7, entity.getCreatedAt());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Room> list(User user) {
        ArrayList<Room> listRoom = new ArrayList<>();
        String sql = "SELECT `room`.`room_id`,\n" + "    `room`.`room_name`,\n" + "    `room`.`code`,\n" + "    `room`.`password`,\n" + "    `room`.`description`,\n" + "    `room`.`uid`,\n" + "    `room`.`created_at`\n" + "FROM `online_quizz`.`room` WHERE uid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomName(rs.getString(2));
                r.setCode(rs.getString(3));
                r.setPassword(rs.getString(4));
                r.setDescription(rs.getString(5));
                r.setUser(user);
                r.setCreatedAt(rs.getTimestamp(7));
                listRoom.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listRoom;
    }

    public ArrayList<Room> listAllRoomExceptOwner(User user) {
        ArrayList<Room> listRoom = new ArrayList<>();
        String sql = "SELECT `room`.`room_id`,\n" + "    `room`.`room_name`,\n" + "    `room`.`code`,\n" + "    `room`.`password`,\n" + "    `room`.`description`,\n" + "    `room`.`uid`,\n" + "    `room`.`created_at`\n" + "FROM `online_quizz`.`room` WHERE uid != ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomName(rs.getString(2));
                r.setCode(rs.getString(3));
                r.setPassword(rs.getString(4));
                r.setDescription(rs.getString(5));
                r.setCreatedAt(rs.getTimestamp(7));
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
        String sql = "SELECT `room`.`room_id`,\n" + "    `room`.`room_name`,\n" + "    `room`.`code`,\n" + "    `room`.`password`,\n" + "    `room`.`description`,\n" + "    `room`.`uid`,\n" + "    `room`.`created_at`,\n" + "    `room`.`updated_at`\n" + "FROM `online_quizz`.`room`\n" + "WHERE code = ? AND password= ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, code);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomName(rs.getString(2));
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
        String sql = "INSERT INTO `online_quizz`.`user_join_room`\n" + "(`uid`,\n" + "`room_id`)\n" + "VALUES\n" + "(?,\n" + "?);\n";

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
            String sql = "SELECT user_join_room.uid, user_join_room.room_id, room.room_name, room.code, room.password, room.description, room.created_at, room.uid, user.username\n" + "                    FROM online_quizz.user_join_room\n" + "                    INNER JOIN online_quizz.room \n" + "                    ON user_join_room.room_id = room.room_id AND user_join_room.uid = ?\n" + "                    INNER JOIN online_quizz.user\n" + "                    ON room.uid = user.uid";
            PreparedStatement stm = null;
            stm = connection.prepareStatement(sql);
            stm.setInt(1, user.getId());
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setRoomName(resultSet.getString("room_name"));
                room.setCode(resultSet.getString("code"));
                room.setPassword(resultSet.getString("password"));
                room.setDescription(resultSet.getString("description"));
                room.setCreatedAt(resultSet.getTimestamp("created_at"));
                User u = new User();
                u.setId(resultSet.getInt("uid"));
                u.setUsername(resultSet.getString("username"));
                room.setUser(u);
                listRoomJoinedByUser.add(room);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listRoomJoinedByUser;
    }


    public Room getRoomByName(Room roomNeedToCheck) {
        String sql = "SELECT `room`.`room_id`,\n" + "    `room`.`room_name`,\n" + "    `room`.`code`,\n" + "    `room`.`password`,\n" + "    `room`.`description`,\n" + "    `room`.`uid`,\n" + "    `room`.`created_at`,\n" + "    `room`.`updated_at`\n" + "FROM `online_quizz`.`room`\n" + "WHERE room_name = ?;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roomNeedToCheck.getRoomName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomName(rs.getString(2));
                r.setDescription(rs.getString(5));
                return r;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<String> listRoomName() {
        ArrayList<String> listRoomName = new ArrayList<>();
        String sql = "SELECT room_name FROM online_quizz.room;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listRoomName.add(rs.getString("room_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listRoomName;
    }

    public ArrayList<Room> getOwnedRoom(User entity) {
        ArrayList<Room> ownedRooms = new ArrayList<>();
        String sqlGetOwnedRooms = "SELECT `room`.`room_id`,\n" + "    `room`.`room_name`,\n" + "    `room`.`code`,\n" + "    `room`.`password`,\n" + "    `room`.`description`,\n" + "    `room`.`created_at`,\n" + "    `room`.`updated_at`\n" + "FROM `online_quizz`.`room`\n" + "WHERE `room`.`uid` = ?";
        try {
            PreparedStatement stmGetOwnedRooms = connection.prepareStatement(sqlGetOwnedRooms);
            stmGetOwnedRooms.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetOwnedRooms.executeQuery();
            while (rs.next()) {
                Room ownedRoom = new Room();
                ownedRoom.setRoomId(rs.getInt("room_id"));
                ownedRoom.setUser(entity);
                ownedRoom.setRoomName(rs.getString("room_name"));
                ownedRoom.setDescription(rs.getString("description"));
                ownedRoom.setCode(rs.getString("code"));
                ownedRoom.setPassword(rs.getString("password"));
                ownedRooms.add(ownedRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownedRooms;
    }

    public ArrayList<Room> getJoinedRooms(User entity) {
        ArrayList<Room> joinedRooms = new ArrayList<>();
        String sqlGetJoinedRooms = "SELECT `user_join_room`.`room_id`\n" + "FROM `online_quizz`.`user_join_room`\n" + "WHERE `user_join_room`.`uid` = ? ";
        try {
            PreparedStatement stmGetJoinedRooms = connection.prepareStatement(sqlGetJoinedRooms);
            stmGetJoinedRooms.setInt(1, entity.getId());
            ResultSet rs = stmGetJoinedRooms.executeQuery();
            while (rs.next()) {
                Room joinedRoom = new Room();
                joinedRoom.setRoomId(rs.getInt("room_id"));
                joinedRooms.add(joinedRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return joinedRooms;
    }

    public void deleteRoom(Room r) {
        try {
            connection.setAutoCommit(false);

            String sqlDeleteTest = "DELETE FROM `online_quizz`.`test` WHERE room_id = ?";
            try (PreparedStatement psDeleteTest = connection.prepareStatement(sqlDeleteTest)) {
                psDeleteTest.setInt(1, r.getRoomId());
                int rowsDeletedTest = psDeleteTest.executeUpdate();
                System.out.println(rowsDeletedTest + " records deleted from test table");
            }


            String sqlDeleteRoom = "DELETE FROM `online_quizz`.`room` WHERE room.room_id = ?";
            try (PreparedStatement psDeleteRoom = connection.prepareStatement(sqlDeleteRoom)) {
                psDeleteRoom.setInt(1, r.getRoomId());
                int rowsDeletedRoom = psDeleteRoom.executeUpdate();
                System.out.println(rowsDeletedRoom + " records deleted from room table");
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

    public void updateRoom(Room r) {
        try {
            String sql = "UPDATE `online_quizz`.`room`\n" +
                    "SET\n" +
                    "`room_name` = ?,\n" +
                    "`description` = ?,\n" +
                    "`updated_at` =?\n" +
                    "WHERE `room_id` =?;\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, r.getRoomName());
            stm.setString(2, r.getDescription());
            stm.setTimestamp(3, r.getUpdatedAt());
            stm.setInt(4, r.getRoomId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Room getRoomById(Room r) {
        String sql = "SELECT \n" +
                "    `room`.`room_name`,\n" +
                "    `room`.`code`,\n" +
                "    `room`.`password`,\n" +
                "    `room`.`description`,\n" +
                "    `room`.`uid`\n" +
                "FROM `online_quizz`.`room` WHERE room_id = ?;\n";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, r.getRoomId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserDBContext userDBContext = new UserDBContext();
                User owner = userDBContext.get(rs.getInt("uid"));
                r.setRoomName(rs.getString("room_name"));
                r.setDescription(rs.getString("description"));
                r.setCode(rs.getString("code"));
                r.setPassword(rs.getString("password"));
                r.setUser(owner);
                return r;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // search Room by Name
    public ArrayList<Room> search(String name) {
        ArrayList<Room> rooms = new ArrayList<>();
        String sql = "select * from room \n" +
                "where room_name like ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt(1));
                r.setRoomName(rs.getString(2));
                r.setCode(rs.getString(3));
                r.setPassword(rs.getString(4));
                UserDBContext udb = new UserDBContext();
                User user = udb.get(rs.getInt("uid"));
                r.setUser(user);
                r.setDescription(rs.getString(5));
                r.setCreatedAt(rs.getTimestamp(7));
                r.setUpdatedAt(rs.getTimestamp(8));
                rooms.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    public ArrayList<Room> listRoomAndOwner() {
        ArrayList<Room> rooms = new ArrayList<>();
        String sqlListRoomAndOwner = "SELECT r.room_id, r.room_name, r.`code`, r.`description`, \n" +
                "u.uid, u.given_name, u.family_name, u.avatar, r.created_at, \n" +
                "r.updated_at FROM online_quizz.room r\n" +
                "INNER JOIN online_quizz.`user` u ON u.uid = r.uid";
        try {
            PreparedStatement stmListRoomAndOwner = connection.prepareStatement(sqlListRoomAndOwner);
            ResultSet rs = stmListRoomAndOwner.executeQuery();
            while(rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomName(rs.getString("room_name"));
                room.setCode(rs.getString("code"));
                room.setDescription(rs.getString("description"));
                room.setCreatedAt(rs.getTimestamp("created_at"));
                room.setUpdatedAt(rs.getTimestamp("updated_at"));
                User owner = new User();
                owner.setFamilyName(rs.getString("family_name"));
                owner.setId(rs.getInt("uid"));
                owner.setGivenName(rs.getString("given_name"));
                owner.setAvatar(rs.getString("avatar"));
                room.setUser(owner);
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
}

