package com.douaiwan.eliminate.handler;

import com.douaiwan.eliminate.enums.ThreadWorkerEnum;
import com.douaiwan.eliminate.handler.base.BaseHandler;
import com.douaiwan.eliminate.modules.ExecutorModule;
import com.douaiwan.eliminate.modules.SystemModule;
import com.douaiwan.eliminate.modules.UserModule;
import com.douaiwan.eliminate.session.interfaces.ISession;
import com.douaiwan.eliminate.task.MessageTask;
import com.google.inject.Inject;

public class LoginHandler extends BaseHandler<MessageTask> {

    private final SystemModule systemModule;
    private final ExecutorModule executorModule;
    private final UserModule userModule;

    @Inject
    public LoginHandler(SystemModule systemModule , ExecutorModule executorModule ,UserModule userModule) {
        super(10001,systemModule);
        this.systemModule = systemModule;
        this.executorModule = executorModule;
        this.userModule = userModule;
    }

    @Override
    public void handle(MessageTask task) {
        /*登陆由 单线程执行*/
        ISession session = task.getSession();
        executorModule.getWork(ThreadWorkerEnum.casher).execute(()->{
            if( session != null ){
                Object user = session.getPlayer();

            }
        });
    }
}
