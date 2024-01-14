package dao;

import Entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBContext extends DBContext<User>{

    @Override
    public User get(User entity) {

        String sql = "SELECT `user`.`uid`,\n" +
                "    `user`.`username`,\n" +
                "    `user`.`email`,\n" +
                "    `user`.`password`,\n" +
                "    `user`.`display_name`,\n" +
                "    `user`.`avartar`,\n" +
                "    `user`.`rid`\n" +
                "FROM `online_quizz`.`user` \n" +
                "where uid = 1;\n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next())
            {
                User user = new User();
                user.setUid(Integer.parseInt(rs.getString(1)));
                user.setUsername(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setDisplayName(rs.getString(5));
                user.setAvatar(rs.getString(6));
                user.setRid(rs.getInt(7));

                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
