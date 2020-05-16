package com.douaiwan.eliminate;

import com.douaiwan.eliminate.server.Server;
import com.google.inject.Guice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMain {
    public static void main( String[] argus){
        Guice.createInjector(new BasicModule());
        new Server().start();
    }
}
