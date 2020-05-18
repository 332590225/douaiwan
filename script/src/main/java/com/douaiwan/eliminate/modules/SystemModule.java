package com.douaiwan.eliminate.modules;

import com.douaiwan.eliminate.config.ServerConfig;
import com.douaiwan.eliminate.dispathers.EventDispather;
import com.douaiwan.eliminate.dispathers.MessageDispather;
import com.douaiwan.eliminate.dispathers.interfaces.IListener;
import com.douaiwan.eliminate.handler.base.BaseHandler;
import com.douaiwan.eliminate.pojo.data.ServerData;
import com.douaiwan.eliminate.pojo.group.Group;
import com.douaiwan.eliminate.scheduler.TimeScheduler;
import com.douaiwan.eliminate.task.EventTask;
import com.douaiwan.eliminate.task.MessageTask;
import com.google.inject.Inject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;
import java.util.Map;

public class SystemModule implements IListener<MessageTask> {

    private Map<Integer,BaseHandler> handlers = new HashMap();
    private final ExecutorModule executorModule;
    private final TimeScheduler scheduler = new TimeScheduler();

    @Inject
    public SystemModule(ExecutorModule executorModule) {
        this.executorModule = executorModule;
        MessageDispather.getInstance().addEventListener(this);
        initRegister();
    }

    private void initRegister(){
        scheduler.addJob(EventTask.TICK,"* * * * * ? *");
        scheduler.addJob(EventTask.DAY_END,"59 59 23 ? * * *");
        scheduler.addJob(EventTask.WEEK_END,"58 59 23 ? * 7 *");
    }

    public void register(int id , BaseHandler handler){
        handlers.put(id,handler);
    }

    @Override
    public void call( MessageTask task) {
        handlers.get(task.getHead()).handle(task);
    }
}
