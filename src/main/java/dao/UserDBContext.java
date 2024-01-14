package dao;

import entity.Role;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDBContext extends DBContext<User>{
    @Override
    public User get(User entity) {
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
                    "FROM `online_quizz`.`user`;\n";
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
                user.setUid(uid);
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setDisplayName(display_name);
                user.setAvatar(avatar);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void insert(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }
}
