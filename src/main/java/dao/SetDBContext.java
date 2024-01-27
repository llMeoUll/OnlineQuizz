package dao;

import entity.Question;
import entity.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SetDBContext extends DBContext<entity.Set> {


    @Override
    public Set get(Set entity) {
        return null;
    }

    @Override
    public ArrayList<Set> list() {
        return null;
    }

    @Override
    public void delete(Set entity) {

    }

    @Override
    public void update(Set entity) {

    }

    @Override
    public void create(Set set) {
        try {
            String query = "INSERT INTO `online_quizz`.`set` (`sname`, `description`, `is_private`, `created_at`, `updated_at`, `uid`) VALUES (?, ?, ?,current_timestamp(), current_timestamp(), ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, set.getSName());
            preparedStatement.setString(2, set.getDescription());
            preparedStatement.setBoolean(3, set.isPrivate());
            preparedStatement.setInt(4, 1);
            preparedStatement.executeUpdate();

//            if (!set.getQuestions().isEmpty()) {
//                List<Question> questions = set.getQuestions();
//
//                String query2 = "SELECT sid FROM online_quizz.set order by sid limit 1";
//                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
//                ResultSet rs = preparedStatement2.executeQuery();
//                while (rs.next()) {
//                    int setId = rs.getInt("sid");
//
//                    for (Question q : questions) {
//                        String query3 = "INSERT INTO `online_quizz`.`question`\n" + "`question`,\n" + "`answer`,\n" + "`sid`,\n" + "`type_id`)\n" + "VALUES\n" + "(?, ?, ?, ?)";
//                        PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
//                        preparedStatement3.setString(1, q.getQuestion());
//                        preparedStatement3.setString(1, q.getQuestion());
//                        preparedStatement3.setInt(3, setId);
//                        preparedStatement3.setInt(4, q.getType().getTypeId());
//                        preparedStatement3.executeUpdate();
//                    }
//                }
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Set entity) {

    }
}
