package com.douaiwan.eliminate.scheduler.job;

import com.douaiwan.eliminate.dispathers.EventDispather;
import com.douaiwan.eliminate.session.Session;
import com.douaiwan.eliminate.task.EventTask;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TickJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        EventDispather.getInstance().dispatherEvent(new EventTask(data.get("type").toString(), null));
    }
}
