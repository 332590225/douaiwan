package com.douaiwan.eliminate.handler.base;

import com.douaiwan.eliminate.modules.SystemModule;
import com.douaiwan.eliminate.task.MessageTask;
import com.google.inject.Inject;

public abstract class BaseHandler<T> {
    protected int messageID;

    public BaseHandler(int messageID , SystemModule systemModule ) {
        this.messageID = messageID;
        systemModule.register(messageID,this);
    }

    public abstract void handle(T task);
}
