package dao;

import entity.Question;
import entity.Role;
import entity.Set;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void insert(Set set) {
        try {
            connection.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `online_quizz`.`set`\n" +
                    "(`sname`,\n" +
                    "`description`,\n" +
                    "`is_private`,\n" +
                    "`uid`,\n" +
                    "`created_at`,\n" +
                    "`updated_at`)\n" +
                    "VALUES\n" +
                    "(?,?,?,?, current_timestamp(), current_timestamp());";
            PreparedStatement stmInsert = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmInsert.setString(1, set.getSName());
            stmInsert.setString(2, set.getDescription());
            stmInsert.setBoolean(3, set.isPrivate());
            stmInsert.setInt(4, set.getUser().getId());
            stmInsert.executeUpdate();

            try (ResultSet generatedKeys = stmInsert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int setId = generatedKeys.getInt(1);
                    ArrayList<Question> questions = set.getQuestions();
                    for (Question question : questions) {
                        String sqlInsertQuestion = "INSERT INTO `online_quizz`.`question`\n" +
                                "(`question`,\n" +
                                "`answer`,\n" +
                                "`type_id`,\n" +
                                "`set_id`)\n" +
                                "VALUES\n" +
                                "(?,?,?,?);";
                        PreparedStatement stmInsertQuestion = connection.prepareStatement(sqlInsertQuestion);
                        stmInsertQuestion.setString(1, question.getQuestion());
                        stmInsertQuestion.setString(2, question.getAnswer());
                        stmInsertQuestion.setInt(3, question.getType().getTypeId());
                        stmInsertQuestion.setInt(4, setId);
                        stmInsertQuestion.executeUpdate();
                    }

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
