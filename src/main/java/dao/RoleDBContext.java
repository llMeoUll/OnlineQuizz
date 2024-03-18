package dao;

import com.lambdaworks.crypto.SCryptUtil;
import entity.Feature;
import entity.Role;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDBContext extends DBContext {
    public Role get(Role entity) {
        String sqlGetIdUserRole =
                "SELECT `role`.`rid`\n" +
                "FROM `online_quizz`.`role`\n" +
                "WHERE `role`.`name` = ?";

        try {
            PreparedStatement stmGetIdUserRole = connection.prepareStatement(sqlGetIdUserRole);
            stmGetIdUserRole.setString(1, entity.getName());
            ResultSet rs = stmGetIdUserRole.executeQuery();
            while(rs.next()) {
                Role role = new Role();
                role.setRId(rs.getInt("rid"));
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Role> getRoles(User entity) {
        ArrayList<Role> roles = new ArrayList<>();
        String sqlGetRoles = "SELECT `role_user_mapping`.`rid`\n" +
                "FROM `online_quizz`.`role_user_mapping`\n" +
                "WHERE `role_user_mapping`.`uid` = ? ";
        try {
            PreparedStatement stmGetRoles = connection.prepareStatement(sqlGetRoles);
            stmGetRoles.setInt(1, entity.getId());
            ResultSet rs = stmGetRoles.executeQuery();
            while(rs.next()) {
                Role role = new Role();
                role.setRId(rs.getInt("rid"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
    public ArrayList<Role> list(String email) throws ClassNotFoundException {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "select r.rid, r.name as rname, f.fid, f.fname, f.url from `user` u\n" +
                    "inner join `role_user_mapping` ru on u.uid = ru.uid\n" +
                    "inner join `role` r on r.rid = ru.rid\n" +
                    "inner join `role_feature_mapping` rf on rf.rid = r.rid\n" +
                    "inner join `feature` f on f.fid = rf.fid\n" +
                    "where u.email = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setRId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));

                Feature f = new Feature();
                f.setFId(rs.getInt("fid"));
                f.setFName(rs.getString("fname"));
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

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
