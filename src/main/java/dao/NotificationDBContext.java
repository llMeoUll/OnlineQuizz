package dao;

import entity.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.User;

public class NotificationDBContext extends DBContext<Notification> {
    public ArrayList<Notification> list(User user) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sqlListNotification = "";
        try {
            PreparedStatement stmListNotification = connection.prepareStatement(sqlListNotification);
            ResultSet rs = stmListNotification.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notifications;
    }

}
