package dao;

import entity.UserViewSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserViewSetDBContext extends DBContext{
    public void view(int uId, int sId) {
        if (checkViewed(uId, sId)) {
            update(uId, sId);
        } else {
            insert(uId, sId);
        }
    }

    public void insert(int uId, int sId) {
        try {
            String sql = "INSERT INTO `online_quizz`.`user_view_set`\n" +
                    "(`uid`,\n" +
                    "`sid`,\n" +
                    "`visited_at`)\n" +
                    "VALUES\n" +
                    "?,?,current_timestamp();";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uId);
            preparedStatement.setInt(2, sId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<UserViewSet> list (int uId){
        String sql = "SELECT * FROM online_quizz.user_view_set\n" +
                "where uid = ?\n" +
                "order by visited_at desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<UserViewSet> userViewSets = new ArrayList<>();
            while (resultSet.next()) {
                UserViewSet userViewSet = new UserViewSet();
                userViewSet.setUId(resultSet.getInt("uid"));
                userViewSet.setSId(resultSet.getInt("sid"));
                userViewSet.setVisitedAt(resultSet.getTimestamp("visited_at"));
                userViewSets.add(userViewSet);
            }
            return userViewSets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void update(int uId, int sId) {
        try {
            String sql = "UPDATE `online_quizz`.`user_view_set`\n" +
                    "SET\n" +
                    "`visited_at` = current_timestamp()\n" +
                    "WHERE `uid` = ? and `sid` = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uId);
            preparedStatement.setInt(2, sId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkViewed(int uId, int sId) {
        String sql = "SELECT * FROM online_quizz.user_view_set\n" +
                "where uid = ? and sid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uId);
            preparedStatement.setInt(2, sId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
