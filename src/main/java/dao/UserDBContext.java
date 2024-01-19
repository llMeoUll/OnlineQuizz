package dao;

import entity.Role;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class UserDBContext extends DBContext<User>{
    @Override
    public User get(User entity) {
        try {
            String sqlGetUser = "SELECT `user`.`uid`,\n" +
                    "    `user`.`username`,\n" +
                    "    `user`.`email`,\n" +
                    "    `user`.`display_name`,\n" +
                    "    `user`.`avartar`,\n" +
                    "    `user`.`rid`\n" +
                    "FROM `online_quizz`.`user`\n" +
                    "WHERE `user`.`username` = ? AND `user`.`password` = ?";
            PreparedStatement stmGetUser = connection.prepareStatement(sqlGetUser);
            stmGetUser.setString(1, entity.getUsername());
            stmGetUser.setString(2, entity.getPassword());
            ResultSet rsGetUser = stmGetUser.executeQuery();
            while(rsGetUser.next()) {
                User user = new User();
                int uid = Integer.parseInt(rsGetUser.getString("uid"));
                String username = rsGetUser.getString("username");
                String email = rsGetUser.getString("email");
                String display_name = rsGetUser.getString("display_name");
                String avatar = rsGetUser.getString("avartar");
                int rid = Integer.parseInt(rsGetUser.getString("rid"));
                Role role = new Role();
                role.setRid(rid);
                user.setUid(uid);
                user.setUsername(username);
                user.setEmail(email);
                user.setDisplayName(display_name);
                user.setAvatar(avatar);
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                    "FROM `online_quizz`.`user`;\n";
            PreparedStatement stmGetListUser = connection.prepareStatement(sqlListUser);
            ResultSet rsListUser = stmGetListUser.executeQuery();
            while(rsListUser.next()) {
                int uid = Integer.parseInt(rsListUser.getString("uid"));
                String username = rsListUser.getString("username");
                String email = rsListUser.getString("email");
                String password = rsListUser.getString("password");
                String display_name = rsListUser.getString("display_name");
                byte[] avatarBytes = rsListUser.getBytes("avartar");

                String avatar = null;
                if(avatarBytes != null) {
                    avatar = Base64.getEncoder().encodeToString(avatarBytes);
                }
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
    public void create(User entity) {

    }

    @Override
    public void delete(User entity) {

    }
}
