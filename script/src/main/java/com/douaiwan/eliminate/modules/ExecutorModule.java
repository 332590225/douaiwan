package com.douaiwan.eliminate.modules;


import com.douaiwan.eliminate.dispathers.EventDispather;
import com.douaiwan.eliminate.dispathers.interfaces.IListener;
import com.douaiwan.eliminate.enums.ThreadWorkerEnum;
import com.douaiwan.eliminate.task.EventTask;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorModule implements IListener<EventTask> {
    private Map<ThreadWorkerEnum, Executor> executors = new HashMap();

    @Inject
    public ExecutorModule() {
        EventDispather.getInstance().addEventListener(this);
        for (ThreadWorkerEnum worker :ThreadWorkerEnum.values() ) {
            executors.put(worker, Executors.newSingleThreadExecutor());
        }
    }

    public Executor getWork(ThreadWorkerEnum type){
        return executors.get(type);
    }

    @Override
    public void call(EventTask eventTask) {

    }
}
