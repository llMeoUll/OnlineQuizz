package dao;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDBContext extends DBContext<User>{
    @Override
    public User get(User entity) {
        try {
            String sqlGetAdmin = "SELECT `user`.`uid`,\n" +
                    "    `user`.`email`,\n" +
                    "    `user`.`username`,\n" +
                    "    `user`.`given_name`,\n" +
                    "    `user`.`family_name`,\n" +
                    "    `user`.`avartar`,\n" +
                    "    `user`.`created_at`,\n" +
                    "    `user`.`updated_at`\n" +
                    "FROM `online_quizz`.`user`\n" +
                    "WHERE `user`.`username` = ? AND `user`.`password` = ?";
            PreparedStatement stmGetAdmin = connection.prepareStatement(sqlGetAdmin);
            stmGetAdmin.setString(1, entity.getUsername());
            stmGetAdmin.setString(2, entity.getPassword());
            ResultSet rs = stmGetAdmin.executeQuery();
            while(rs.next()) {
                User user = new User();
                String username = rs.getString("username");
                int uid = Integer.parseInt(rs.getString("uid"));
                user.setUsername(username);
                user.setId(uid);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<User> list() {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void insert(User entity) {

    }
}
