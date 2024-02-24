package dao;

import entity.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeDBContext extends DBContext {
    public ArrayList<Type> list() {
        ArrayList<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM online_quizz.type;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Type type = new Type();
                type.setTypeId(resultSet.getInt("type_id"));
                type.setTypeName(resultSet.getString("type_name"));
                types.add(type);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return types;
    }
}
