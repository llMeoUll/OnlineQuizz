package dao;

import entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeDBContext extends DBContext{
    public Type get(int typeId, Connection connection) {
        Type type = new Type();
        String sql = "SELECT * FROM online_quizz.type WHERE type_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, typeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                type.setTypeId(resultSet.getInt("type_id"));
                type.setTypeName(resultSet.getString("type_name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return type;
    }
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
