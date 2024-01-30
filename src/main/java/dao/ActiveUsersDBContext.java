package dao;

import controller.admin.schedule.DailyUpdateActiveUsers;
import controller.admin.schedule.HourlyUpdateActiveUsers;
import entity.ActiveUsers;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class ActiveUsersDBContext extends DBContext<ActiveUsers> {
    @Override
    public ActiveUsers get(ActiveUsers entity) {
        return null;
    }

    @Override
    public ArrayList<ActiveUsers> list() {
        return null;
    }

    @Override
    public void delete(ActiveUsers entity) {

    }

    @Override
    public void update(ActiveUsers entity) {

    }

    @Override
    public void insert(ActiveUsers entity) {

    }

    public void update() {
        try {
            String sqlUpdateEachDay = "UPDATE `online_quizz`.`active_users`\n" +
                    "SET `active_user` = 0";
            PreparedStatement stmUpdateEachDay = connection.prepareStatement(sqlUpdateEachDay);
            stmUpdateEachDay.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int numberOfUserActive) {
        try{
            LocalTime localTime = LocalTime.now();
            int hour = localTime.getHour();
            if(hour == 0) {
                hour = 23;
            }
            else {
                hour--;
            }
            String id_hour = String.valueOf(hour);
            String sqlHourlyUpdate = "UPDATE `online_quizz`.`active_users`\n" +
                    "SET `active_user` = ?\n" +
                    "WHERE `id_hour` = ?;\n";
            PreparedStatement stmHourlyUpdate = connection.prepareStatement(sqlHourlyUpdate);
            stmHourlyUpdate.setString(1, String.valueOf(numberOfUserActive));
            stmHourlyUpdate.setString(2, id_hour);
            stmHourlyUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Integer> numberOfActiveUser() {
        ArrayList<Integer> listActiveUsers = new ArrayList<>();
        try {
            String sqlGetActiveUsers = "SELECT `active_users`.`active_user`\n" +
                    "FROM `online_quizz`.`active_users`;\n";
            PreparedStatement stmGetActiveUsers = connection.prepareStatement(sqlGetActiveUsers);
            ResultSet rs = stmGetActiveUsers.executeQuery();
            while(rs.next()) {
                int numberOfActiveUser = Integer.parseInt(rs.getString("active_user"));
                listActiveUsers.add(numberOfActiveUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listActiveUsers;
    }



}
