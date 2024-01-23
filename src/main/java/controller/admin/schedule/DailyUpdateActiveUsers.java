package controller.admin.schedule;

import dao.ActiveUsersDBContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class DailyUpdateActiveUsers implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        try {
            activeUsersDBContext.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
