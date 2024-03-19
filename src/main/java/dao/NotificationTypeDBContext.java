package dao;

import entity.NotificationType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationTypeDBContext extends DBContext<NotificationType>{
    public NotificationType get(int typeId) {
        String sqlGetNotificationType = "SELECT * FROM online_quizz.notification_type\n" +
                "WHERE `notification_type`.`type_id` = ?;";
        try {
            PreparedStatement stmGetNotificationType = connection.prepareStatement(sqlGetNotificationType);
            stmGetNotificationType.setInt(1, typeId);
            ResultSet rs = stmGetNotificationType.executeQuery();
            while(rs.next()) {
                NotificationType notificationType = new NotificationType();
                notificationType.setNotificationTypeId(rs.getInt("type_id"));
                notificationType.setAction(rs.getString("action"));
                return notificationType;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }
}
