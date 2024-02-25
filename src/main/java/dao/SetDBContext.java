package dao;

import entity.HashTag;
import entity.Question;
import entity.Set;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SetDBContext extends DBContext {
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
            while (rs.next()) {
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

    public Set get(int setId) {
        String sql = "SELECT * FROM `online_quizz`.`set` WHERE `set`.`sid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            ResultSet rs = stm.executeQuery();
            if (!rs.next()) {
                return null;
            } else {
                Set set = new Set();
                set.setSId(rs.getInt("sid"));
                set.setSName(rs.getString("sname"));
                set.setDescription(rs.getString("description"));
                set.setPrivate(rs.getBoolean("is_private"));
                ArrayList<Question> questions = new QuestionDBContext().list(setId, connection);
                set.setQuestions(questions);
                ArrayList<HashTag> hashTags = new HashtagDBContext().list(setId, connection);
                set.setHashTags(hashTags);
                return set;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                        hashtagDBContext.insertAll(set.getHashTags(), setId, connection);
                        QuestionDBContext questionDBContext = new QuestionDBContext();
                        questionDBContext.insertAll(set.getQuestions(), setId, connection);
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

    public void update(Set set) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String updateSetQuery = "UPDATE `online_quizz`.`set`\n" +
                    "SET\n" +
                    "`sname` = ?,\n" +
                    "`description` = ?,\n" +
                    "`is_private` = ?,\n" +
                    "`updated_at` = current_timestamp()\n" +
                    "WHERE `sid` = ?;";
            try (PreparedStatement setStatement = connection.prepareStatement(updateSetQuery)) {
                setStatement.setString(1, set.getSName());
                setStatement.setString(2, set.getDescription());
                setStatement.setBoolean(3, set.isPrivate());
                setStatement.setInt(4, set.getSId());
                setStatement.executeUpdate();

                // Now, update questions associated with this set
                //delete all hashtag set mapping
                HashtagSetMappingDBContext hashtagSetMappingDBContext = new HashtagSetMappingDBContext();
                hashtagSetMappingDBContext.deleteAll(set.getSId(), connection);
                //delete all hashtag
                HashtagDBContext hashtagDBContext = new HashtagDBContext();
                ArrayList<HashTag> oldHashTags = hashtagDBContext.list(set.getSId(), connection);
                if (oldHashTags.size() > 0) {
                    hashtagDBContext.deleteAll(oldHashTags, connection);
                }
                hashtagDBContext.insertAll(set.getHashTags(), set.getSId(), connection);

                QuestionDBContext questionDBContext = new QuestionDBContext();
                //delete all question
                questionDBContext.deleteAll(set.getSId(), connection);
                questionDBContext.insertAll(set.getQuestions(), set.getSId(), connection);
                connection.commit(); // Commit the transaction for updating set
            } catch (SQLException e) {
                connection.rollback(); // Rollback if there's an exception
                throw new RuntimeException("Failed to update set.", e);
            }
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit mode
        }
    }

    public boolean isOwner(int userId, int setId) {
        String sql = "SELECT * FROM `online_quizz`.`set` WHERE `set`.`sid` = ? AND `set`.`uid` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
