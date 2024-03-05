package controller.admin.schedule;

import dao.ActiveUsersDBContext;
import listener.ActiveSessionCounter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HourlyUpdateActiveUsers implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ActiveUsersDBContext activeUsersDBContext = new ActiveUsersDBContext();
        int numberOfActiveUser = ActiveSessionCounter.getActiveSessions();
        try {
            activeUsersDBContext.update(numberOfActiveUser);
            ActiveSessionCounter.resetActiveSessions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
