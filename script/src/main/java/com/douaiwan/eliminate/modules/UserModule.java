package com.douaiwan.eliminate.modules;

import com.douaiwan.eliminate.dispathers.EventDispather;
import com.douaiwan.eliminate.dispathers.interfaces.IListener;
import com.douaiwan.eliminate.task.EventTask;
import com.google.inject.Inject;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserModule implements IListener<EventTask> {
    private final SystemModule systemModule;
    @Inject
    public UserModule( SystemModule systemModule) {
        this.systemModule = systemModule;
        EventDispather.getInstance().addEventListener(this);
    }

    @Override
    public void call(EventTask eventTask) {

    }
}
