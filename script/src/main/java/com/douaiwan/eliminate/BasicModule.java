package com.douaiwan.eliminate;

import com.douaiwan.eliminate.handler.LoginHandler;
import com.douaiwan.eliminate.modules.*;
import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Singleton;

import javax.security.auth.spi.LoginModule;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        /*Modules*/
        bind(SystemModule.class).asEagerSingleton();
        bind(ExecutorModule.class).asEagerSingleton();
        bind(UserModule.class).asEagerSingleton();
        bind(GroupModule.class).asEagerSingleton();
        bind(DbModule.class).asEagerSingleton();

        /*Handlers*/
        bind(LoginHandler.class).asEagerSingleton();
    }
}
