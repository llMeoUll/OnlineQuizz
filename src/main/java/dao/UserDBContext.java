package dao;

import entity.*;


import com.lambdaworks.crypto.SCryptUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext extends DBContext {
    public User get(int id) {
        String query = "SELECT * FROM `online_quizz`.`user` "
                + "WHERE uid = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    User user = initUserInfo(resultSet);
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User get(String email) {
        try {
            String sql = "SELECT * FROM `online_quizz`.`user` "
                    + "WHERE `email` = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                User user = initUserInfo(resultSet);
                RoleDBContext roleDBContext = new RoleDBContext();
                ArrayList<Role> roles = roleDBContext.list(user.getEmail());
                user.setRoles(roles);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User get(String email, String password) {
        try {
            String sql = "SELECT * FROM `online_quizz`.`user` "
                    + "WHERE `email` = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                User user = initUserInfo(resultSet);
                if (SCryptUtil.check(password, user.getPassword())) {
                    RoleDBContext roleDBContext = new RoleDBContext();
                    ArrayList<Role> roles = roleDBContext.list(user.getEmail());
                    user.setRoles(roles);
                    return user;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetListUser = "SELECT * FROM `online_quizz`.`user`";
        try {
            PreparedStatement stmGetListUser = connection.prepareStatement(sqlGetListUser);
            ResultSet rs = stmGetListUser.executeQuery();
            while (rs.next()) {
                User user = initUserInfo(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public ArrayList<User> list(String email) {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetUsersByEmail = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avatar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`email` LIKE ? OR `user`.`email` LIKE ? OR `user`.`email` LIKE ?";
        try {
            PreparedStatement stmGetUsersByEmail = connection.prepareStatement(sqlGetUsersByEmail);
            stmGetUsersByEmail.setString(1, "%" + email);
            stmGetUsersByEmail.setString(2, "%" + email + "%");
            stmGetUsersByEmail.setString(3, email + "%");
            ResultSet rs = stmGetUsersByEmail.executeQuery();
            while (rs.next()) {
                User user = initUserInfo(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void insert(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`user`\n" +
                    "(\n" +
                    "`email`,\n" +
                    "`username`,\n" +
                    "`password`,\n" +
                    "`given_name`,\n" +
                    "`family_name`,\n" +
                    "`is_verify`,\n" +
                    "`avatar`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stmInsert = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmInsert.setString(1, entity.getEmail());
            stmInsert.setString(2, entity.getUsername());
            stmInsert.setString(3, entity.getPassword());
            stmInsert.setString(4, entity.getGivenName());
            stmInsert.setString(5, entity.getFamilyName());
            stmInsert.setBoolean(6, entity.isVerified());
            stmInsert.setString(7, entity.getAvatar());

            stmInsert.executeUpdate();

            try (ResultSet generatedKeys = stmInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Role role = new Role();
                    role.setName("User");
                    RoleDBContext roleDBContext = new RoleDBContext();
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, roleDBContext.get(role).getRId());
                    stmInsertRole.setInt(2, userId);
                    stmInsertRole.executeUpdate();
                }
            }
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private User initUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("uid"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setGivenName(resultSet.getString("given_name"));
        user.setFamilyName(resultSet.getString("family_name"));
        user.setAvatar(resultSet.getString("avatar"));
        user.setVerified(resultSet.getBoolean("is_verify"));
        user.setCreatedAt(resultSet.getTimestamp("created_at"));
        user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        return user;
    }

    public void update(User entity) {
        try {
            String sqlUpdateUser = "UPDATE `online_quizz`.`user`\n" +
                    "SET\n" +
                    "`email` = ?,\n" +
                    "`username` = ?,\n" +
                    "`given_name` = ?,\n" +
                    "`family_name` = ?,\n" +
                    "`updated_at` = ?\n" +
                    "WHERE `uid` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setString(1, entity.getEmail());
            stmUpdateUser.setString(2, entity.getUsername());
            stmUpdateUser.setString(3, entity.getGivenName());
            stmUpdateUser.setString(4, entity.getFamilyName());
            stmUpdateUser.setTimestamp(5, entity.getUpdatedAt());
            stmUpdateUser.setInt(6, entity.getId());
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String email, String passwordHash) {
        String sqlUpdatePassword = "UPDATE `online_quizz`.`user`\n" +
                "SET\n" +
                "`password` = ?,\n" +
                "`updated_at` = current_timestamp()\n" +
                "WHERE `email` = ?;";
        try {
            PreparedStatement stmUpdatePassword = connection.prepareStatement(sqlUpdatePassword);
            stmUpdatePassword.setString(1, passwordHash);
            stmUpdatePassword.setString(2, email);
            stmUpdatePassword.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User entity) {
        String sqlDeleteUser = "DELETE FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`uid` = ?;";
        try {
            PreparedStatement stmDeleteUser = connection.prepareStatement(sqlDeleteUser);
            stmDeleteUser.setInt(1, entity.getId());
            stmDeleteUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // if email is existed, return false
    public boolean checkEmail(String email) {
        String sql = "SELECT email FROM user\n" +
                "where email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // if username is existed, return false
    public boolean checkUsername(String userName) {
        String sql = "SELECT username FROM user\n" +
                "where username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public ArrayList<User> getNewUserInWeek() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetNewUserInWeek = "SELECT *\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE WEEK(created_at) = WEEK(NOW()) AND DAYOFWEEK(created_at) <= DAYOFWEEK(NOW());";
        try {
            PreparedStatement stmGetNewUserInWeek = connection.prepareStatement(sqlGetNewUserInWeek);
            ResultSet rs = stmGetNewUserInWeek.executeQuery();
            while (rs.next()) {
                User user = initUserInfo(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    // countSetByUid
    public int CountSet(int uid) {
        String sqlCountSet = "SELECT count(sid) as numberofset from user u\n" +
                "inner join `set` as s on u.uid = s.uid\n" +
                "where u.uid = ?";
        try {
            PreparedStatement stmCountSet = connection.prepareStatement(sqlCountSet);
            stmCountSet.setInt(1, uid);

            ResultSet resultSet = stmCountSet.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("numberofset");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // countRoom by uid
    public int CountRoom(int uid) {
        String sqlCountRoom = "SELECT count(room_id) as numberofroom from user u\n" +
                "inner join room as r on u.uid = r.uid\n" +
                "where u.uid = ?";
        try {
            PreparedStatement stmCountRoom = connection.prepareStatement(sqlCountRoom);
            stmCountRoom.setInt(1, uid);

            ResultSet resultSet = stmCountRoom.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("numberofroom");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // search by name or email
    public ArrayList<User> search(String query) {
        ArrayList<User> users = new ArrayList<>();
        String sqlSearchUsersByName = "SELECT `user`.`uid`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`given_name`,\n" +
                "    `user`.`family_name`,\n" +
                "    `user`.`avatar`,\n" +
                "    `user`.`created_at`,\n" +
                "    `user`.`updated_at`\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`username` LIKE ? or `user`.`email` like ?";
        try {
            PreparedStatement stmSearchUsersByName = connection.prepareStatement(sqlSearchUsersByName);
            stmSearchUsersByName.setString(1, "%" + query + "%");
            stmSearchUsersByName.setString(2, "%" + query + "%");
            ResultSet rs = stmSearchUsersByName.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("uid"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setGivenName(rs.getString("given_name"));
                user.setFamilyName(rs.getString("family_name"));
                user.setAvatar(rs.getString("avatar"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void updateUserProfile(User entity) {
        try {
            String sqlUpdateUser = "UPDATE `online_quizz`.`user`\n" +
                    "SET\n" +
                    "`email` = ?,\n" +
                    "`username` = ?,\n" +
                    "`given_name` = ?,\n" +
                    "`family_name` = ?,\n" +
                    "`password` = ?,\n" +
                    "`avatar` = ?,\n" +
                    "`updated_at` = ?\n" +
                    "WHERE `uid` = ?;";
            PreparedStatement stmUpdateUser = connection.prepareStatement(sqlUpdateUser);
            stmUpdateUser.setString(1, entity.getEmail());
            stmUpdateUser.setString(2, entity.getUsername());
            stmUpdateUser.setString(3, entity.getGivenName());
            stmUpdateUser.setString(4, entity.getFamilyName());
            stmUpdateUser.setString(5, entity.getPassword());
            stmUpdateUser.setString(6, entity.getAvatar());
            stmUpdateUser.setTimestamp(7, entity.getUpdatedAt());
            stmUpdateUser.setInt(8, entity.getId());
            stmUpdateUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifiedEmail(String email) {
        String sqlVerifiedEmail = "UPDATE `online_quizz`.`user`\n" +
                "SET\n" +
                "`is_verify` = 1\n" +
                "WHERE `email` = ?;";
        try {
            PreparedStatement stmVerifiedEmail = connection.prepareStatement(sqlVerifiedEmail);
            stmVerifiedEmail.setString(1, email);
            stmVerifiedEmail.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAdmin(String roleName) {
        ArrayList<User> admins = new ArrayList<>();
        String sqlGetAdmin = "SELECT u.uid, u.email FROM online_quizz.role r\n" +
                "inner join online_quizz.role_user_mapping m on r.rid = m.rid\n" +
                "inner join online_quizz.`user` u on u.uid = m.uid\n" +
                "where name = ?";
        try {
            PreparedStatement stmGetAdmin = connection.prepareStatement(sqlGetAdmin);
            stmGetAdmin.setString(1, roleName);
            ResultSet rs = stmGetAdmin.executeQuery();
            while(rs.next()) {
                User admin = new User();
                admin.setId(rs.getInt("uid"));
                admin.setEmail(rs.getString("email"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admins;
    }

    public ArrayList<User> list(Room room) {
        ArrayList<User> usersJoinedRoom = new ArrayList<>();
        String sqlListUsersJoinedRoom = "SELECT ujr.uid FROM online_quizz.room r\n" +
                "INNER JOIN online_quizz.`user_join_room` ujr ON r.room_id = ujr.room_id\n" +
                "WHERE r.room_id = ?";
        try {
            PreparedStatement stmListUsersJoinedRoom = connection.prepareStatement(sqlListUsersJoinedRoom);
            stmListUsersJoinedRoom.setInt(1, room.getRoomId());
            ResultSet rs = stmListUsersJoinedRoom.executeQuery();
            while(rs.next()) {
                int uid = rs.getInt("uid");
                User userJoinedRoom = get(uid);
                usersJoinedRoom.add(userJoinedRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usersJoinedRoom;
    }
}