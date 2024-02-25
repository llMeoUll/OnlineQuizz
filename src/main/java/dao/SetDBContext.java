package dao;

import entity.Set;
import entity.User;
import org.checkerframework.checker.units.qual.A;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SetDBContext extends DBContext{
    public ArrayList<Set> getOwnedSet(User entity) {
        ArrayList<Set> ownedSets = new ArrayList<>();
        String sqlGetOwnedSet = "SELECT `set`.`sid`,\n" +
                "    `set`.`sname`,\n" +
                "    `set`.`description`,\n" +
                "    `set`.`is_private`,\n" +
                "    `set`.`uid`,\n" +
                "    `set`.`created_at`,\n" +
                "    `set`.`updated_at`\n" +
                "FROM `online_quizz`.`set`\n" +
                "WHERE `set`.`uid` = ?";
        try {
            PreparedStatement stmGetOwnedSet = connection.prepareStatement(sqlGetOwnedSet);
            stmGetOwnedSet.setString(1, String.valueOf(entity.getId()));
            ResultSet rs = stmGetOwnedSet.executeQuery();
            while(rs.next()) {
                Set ownedSet = new Set();
                ownedSet.setSId(rs.getInt("sid"));
                ownedSet.setSName(rs.getString("sname"));
                ownedSet.setDescription(rs.getString("description"));
                ownedSet.setPrivate(rs.getBoolean("is_private"));
                ownedSet.setUser(entity);
                ownedSets.add(ownedSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownedSets;
    }

    public void insert(Set set) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String insertSetQuery = "INSERT INTO `online_quizz`.`set`\n" +
                    "(`sname`,\n" +
                    "`description`,\n" +
                    "`is_private`,\n" +
                    "`uid`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`)\n" +
                    "VALUES\n" +
                    "(?,?,?,?, current_timestamp(), current_timestamp());";
            try (PreparedStatement setStatement = connection.prepareStatement(insertSetQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                setStatement.setString(1, set.getSName());
                setStatement.setString(2, set.getDescription());
                setStatement.setBoolean(3, set.isPrivate());
                setStatement.setInt(4, set.getUser().getId());
                setStatement.executeUpdate();

                try (ResultSet generatedKeys = setStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int setId = generatedKeys.getInt(1);
                        // Now, insert questions associated with this set
                        HashtagDBContext hashtagDBContext = new HashtagDBContext();
                        hashtagDBContext.insertHashtags(set.getHashTags(), setId, connection);
                        QuestionDBContext questionDBContext = new QuestionDBContext();
                        questionDBContext.insertQuestions(set.getQuestions(), setId, connection);
                    }
                    connection.commit(); // Commit the transaction for inserting set
                } catch (SQLException e) {
                    connection.rollback(); // Rollback if there's an exception
                    throw new RuntimeException("Failed to insert set.", e);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to set auto-commit mode.", e);
            }
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit mode
        }
    }
    public ArrayList<Set> list() {
        ArrayList<Set> sets = new ArrayList<>();
        try {
            String sqlGetListSet = "SELECT s.sid, s.sname, s.`description`, s.is_private, s.uid, s.created_at, " +
                    "s.updated_at, u.avatar, u.family_name, u.given_name FROM online_quizz.`set` s\n" +
                    "INNER JOIN `online_quizz`.`user` u ON s.uid = u.uid";
            PreparedStatement stmGetListSet = connection.prepareStatement(sqlGetListSet);
            ResultSet rs = stmGetListSet.executeQuery();
            while(rs.next()) {
                Set set = new Set();
                User user = new User();
                user.setId(rs.getInt("uid"));
                user.setAvatar(rs.getString("avatar"));
                user.setGivenName(rs.getString("given_name"));
                user.setFamilyName(rs.getString("family_name"));
                set.setUser(user);
                set.setSId(rs.getInt("sid"));
                set.setSName(rs.getString("sname"));
                set.setDescription(rs.getString("description"));
                set.setPrivate(rs.getBoolean("is_private"));
                set.setCreatedAt(rs.getTimestamp("created_at"));
                set.setUpdatedAt(rs.getTimestamp("updated_at"));
                sets.add(set);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sets;
    }

    public void delete(Set set) {
        String sqlDeleteSet = "DELETE FROM `online_quizz`.`set`\n" +
                "WHERE `sid` = ?;";
        try {
            PreparedStatement stm = connection.prepareStatement(sqlDeleteSet);
            stm.setInt(1, set.getSId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
