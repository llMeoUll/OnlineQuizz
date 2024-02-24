package dao;

import entity.*;


import com.lambdaworks.crypto.SCryptUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
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
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
                    return user;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetListUser = "SELECT * FROM `online_quizz`.`user`";
        try {
            PreparedStatement stmGetListUser = connection.prepareStatement(sqlGetListUser);
            ResultSet rs = stmGetListUser.executeQuery();
            while(rs.next()) {
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
            while(rs.next()) {
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
                    RoleDBConext roleDBConext = new RoleDBConext();
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, roleDBConext.get(role).getRId());
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
    public void delete(User entity) {
        String sqlDeleteUser = "DELETE FROM `online_quizz`.`user`\n" +
                "WHERE `user`.`uid` = ?;";
        PreparedStatement stmDeleteUser = null;
        try {
            stmDeleteUser = connection.prepareStatement(sqlDeleteUser);
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
    public ArrayList<User> getNewUserInWeek() {
        ArrayList<User> users = new ArrayList<>();
        String sqlGetNewUserInWeek = "SELECT *\n" +
                "FROM `online_quizz`.`user`\n" +
                "WHERE WEEK(created_at) = WEEK(NOW()) AND DAYOFWEEK(created_at) <= DAYOFWEEK(NOW());";
        try {
            PreparedStatement stmGetNewUserInWeek = connection.prepareStatement(sqlGetNewUserInWeek);
            ResultSet rs = stmGetNewUserInWeek.executeQuery();
            while(rs.next()) {
                User user = initUserInfo(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}