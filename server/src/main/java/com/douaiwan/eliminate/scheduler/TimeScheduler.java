package com.douaiwan.eliminate.scheduler;

import com.douaiwan.eliminate.scheduler.job.TickJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TimeScheduler {
    private Scheduler scheduler;
    public TimeScheduler(){
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    //"* * * * * ? *"
    public void addJob(String name , String corn ) {
        JobDataMap data = new JobDataMap();
        data.put("type",name);
        JobKey jobKeyA = new JobKey(name, "group");
        JobDetail jobA = JobBuilder.newJob(TickJob.class).withIdentity(jobKeyA).build();
        Trigger trigger1 = TriggerBuilder
                .newTrigger().usingJobData(data)
                .withIdentity(name, "group")
                .withSchedule(CronScheduleBuilder.cronSchedule(corn))
                .build();
        try {
            scheduler.scheduleJob(jobA, trigger1);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

