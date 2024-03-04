package listener;

import controller.admin.schedule.DailyUpdateActiveUsers;
import controller.admin.schedule.HourlyUpdateActiveUsers;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class AutoSchedule implements ServletContextListener {
    public static void scheduleDailyUpdate() {
        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            if(!scheduler.checkExists(JobKey.jobKey("updateDailyActiveUser"))) {
                Trigger triggerDailyUpdateActiveUser = TriggerBuilder.newTrigger()
                        .withIdentity("dailyTrigger", "UpdateActiveUsers")
                        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                        .build();

                JobDetail jobDailyUpdateActiveUser = JobBuilder.newJob(DailyUpdateActiveUsers.class)
                        .withIdentity("updateDailyActiveUser", "UpdateActiveUsers")
                        .build();
                scheduler.scheduleJob(jobDailyUpdateActiveUser, triggerDailyUpdateActiveUser);
            }
            if(!scheduler.checkExists(JobKey.jobKey("updateHourlyActiveUser"))) {
                Trigger triggerHourlyUpdateActiveUser = TriggerBuilder.newTrigger()
                        .withIdentity("hourlyTrigger", "UpdateActiveUsers")
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                        .build();

                JobDetail jobHourlyUpdateActiveUser = JobBuilder.newJob(HourlyUpdateActiveUsers.class)
                        .withIdentity("updateHourlyActiveUser", "UpdateActiveUsers")
                        .build();
                scheduler.scheduleJob(jobHourlyUpdateActiveUser, triggerHourlyUpdateActiveUser);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduleDailyUpdate();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            StdSchedulerFactory.getDefaultScheduler().shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
