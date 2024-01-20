package dao;

import com.lambdaworks.crypto.SCryptUtil;
import entity.Feature;
import entity.Role;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext extends DBContext<User>{
    @Override
    public User get(User entity) {
        try {
            String sql = "SELECT `user`.`email`, `user`.`username`, `user`.`password`  \n" +
                    "FROM `user`\n" +
                    "where `email` = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, entity.getEmail());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                if (SCryptUtil.check(entity.getPassword(), user.getPassword())) {
                    return user;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sqlListUser = "SELECT `user`.`uid`,\n" +
                    "    `user`.`username`,\n" +
                    "    `user`.`email`,\n" +
                    "    `user`.`password`,\n" +
                    "    `user`.`display_name`,\n" +
                    "    `user`.`avartar`,\n" +
                    "    `user`.`rid`\n" +
                    "FROM `user`;\n";
            PreparedStatement stmGetListUser = connection.prepareStatement(sqlListUser);
            ResultSet rsListUser = stmGetListUser.executeQuery();
            while(rsListUser.next()) {
                int uid = Integer.parseInt(rsListUser.getString("uid"));
                String username = rsListUser.getString("username");
                String email = rsListUser.getString("email");
                String password = rsListUser.getString("password");
                String display_name = rsListUser.getString("display_name");
                String avatar = rsListUser.getString("avatar");
                Role role = new Role();
                int rid = Integer.parseInt(rsListUser.getString("rid"));
                User user = new User();
                user.setId(uid);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
//                user.setName(display_name);
                user.setPicture(avatar);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
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
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stmInsert = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmInsert.setString(1, entity.getEmail());
            stmInsert.setString(2, entity.getUsername());
            stmInsert.setString(3, entity.getPassword());
            stmInsert.setString(4, entity.getGiven_name());
            stmInsert.setString(5, entity.getFamily_name());
            stmInsert.executeUpdate();

            try (ResultSet generatedKeys = stmInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, 2);
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

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    public ArrayList<Role> getRolesAndFeatures(String username) throws ClassNotFoundException {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "select r.rid, r.name as rname, f.fid, f.url from `user` u\n" +
                    "inner join `role_user_mapping` ru on u.uid = ru.uid\n" +
                    "inner join `role` r on r.rid = ru.rid\n" +
                    "inner join `role_feature_mapping` rf on rf.rid = r.rid\n" +
                    "inner join `feature` f on f.fid = rf.fid\n" +
                    "where u.username = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRid(rs.getInt("rid"));
                r.setName(rs.getString("rname"));

                Feature f = new Feature();
                f.setFid(rs.getInt("fid"));
                f.setUrl(rs.getString("url"));

                r.getFeatures().add(f);
                roles.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

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

    public void insertGoogleUser(User entity) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`user`\n" +
                    "(\n" +
                    "`email`,\n" +
                    "`given_name`,\n" +
                    "`family_name`,\n" +
                    "`avartar`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`\n" +
                    ")\n" +
                    "VALUES\n" +
                    "(?, ?, ?, ?, current_timestamp(), current_timestamp());\n";
            PreparedStatement stm = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, entity.getEmail());
            stm.setString(2, entity.getGiven_name());
            stm.setString(3, entity.getFamily_name());
            stm.setString(4, entity.getPicture());
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    String sqlInsertRole = "INSERT INTO `online_quizz`.`role_user_mapping`\n" +
                            "(`rid`,\n" +
                            "`uid`)\n" +
                            "VALUES\n" +
                            "(?, ?);\n";
                    PreparedStatement stmInsertRole = connection.prepareStatement(sqlInsertRole);
                    stmInsertRole.setInt(1, 2);
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
}
