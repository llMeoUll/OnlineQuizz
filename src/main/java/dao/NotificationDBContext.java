package dao;

import entity.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.NotificationType;
import entity.User;

public class NotificationDBContext extends DBContext<Notification> {
    public ArrayList<Notification> list(User user) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sqlListNotification = "SELECT n.noti_id, n.`from`, n.url, num.is_read, n.type_id, nt.`action`, num.`to`, n.`content`\n" +
                "                FROM online_quizz.notification n\n" +
                "                INNER JOIN online_quizz.`notification_user_mapping` num\n" +
                "                ON n.noti_id = num.noti_id\n" +
                "                INNER JOIN online_quizz.`notification_type` nt \n" +
                "                ON nt.type_id = n.type_id\n" +
                "                WHERE num.`to` = ?\n" +
                "                ORDER BY n.noti_id DESC";
        try {
            PreparedStatement stmListNotification = connection.prepareStatement(sqlListNotification);
            stmListNotification.setInt(1, user.getId());
            ResultSet rs = stmListNotification.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("noti_id"));
                UserDBContext userDBContext = new UserDBContext();
                User from = userDBContext.get(rs.getInt("from"));

                //close connection
                userDBContext.closeConnection();

                notification.setFrom(from);
                notification.setUrl(rs.getString("url"));
                NotificationType notificationType = new NotificationType();
                notificationType.setAction(rs.getString("action"));
                notificationType.setNotificationTypeId(rs.getInt("type_id"));
                notification.setType(notificationType);
                notification.setContent(rs.getString("content"));
                notification.setRead(rs.getBoolean("is_read"));
                notifications.add(notification);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notifications;
    }

    public void insert(Notification notification) {
        try {
            connection.setAutoCommit(false);
            String sqlInsertNotification = "INSERT INTO `online_quizz`.`notification`\n" +
                    "(`from`,\n" +
                    "`url`,\n" +
                    "`type_id`,\n" +
                    "`content`)\n" +
                    "VALUES\n" +
                    "(?,?,?,?);";
            PreparedStatement stmInsertNotification = connection.prepareStatement(sqlInsertNotification, PreparedStatement.RETURN_GENERATED_KEYS);
            stmInsertNotification.setInt(1, notification.getFrom().getId());
            stmInsertNotification.setString(2, notification.getUrl());
            stmInsertNotification.setInt(3, notification.getType().getNotificationTypeId());
            stmInsertNotification.setString(4, notification.getContent());
            stmInsertNotification.executeUpdate();
            ResultSet getGenerateKeys = stmInsertNotification.getGeneratedKeys();
            if(getGenerateKeys.next()) {
                int notificationId = getGenerateKeys.getInt(1);
                for(User receiver : notification.getTos()) {
                    String sqlInsertNotificationUserMapping = "INSERT INTO `online_quizz`.`notification_user_mapping`\n" +
                            "(`noti_id`,\n" +
                            "`to`,\n" +
                            "`is_read`)\n" +
                            "VALUES\n" +
                            "(?,?,?);";
                    PreparedStatement stmInsertNotificationUserMapping = connection.prepareStatement(sqlInsertNotificationUserMapping);
                    stmInsertNotificationUserMapping.setInt(1, notificationId);
                    stmInsertNotificationUserMapping.setInt(2, receiver.getId());
                    stmInsertNotificationUserMapping.setBoolean(3, false);
                    stmInsertNotificationUserMapping.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }

}
