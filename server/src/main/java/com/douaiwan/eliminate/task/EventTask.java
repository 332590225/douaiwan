package com.douaiwan.eliminate.task;

import com.douaiwan.eliminate.session.interfaces.ISession;
import io.netty.buffer.ByteBuf;


public class EventTask<T> {
    public static final String TICK = "TICK"; //服务器的定时TICK
    public static final String DAY_END = "DAY_END"; //服务器当日时间结束
    public static final String WEEK_END = "WEEK_END"; //服务器周结束

    private String evtID;
    private T evtData;

    public EventTask(String evtID, T evtData) {
        this.evtID = evtID;
        this.evtData = evtData;
    }

    public String getEvtID() {
        return evtID;
    }

    public void setEvtID(String evtID) {
        this.evtID = evtID;
    }

    public T getEvtData() {
        return evtData;
    }

    public void setEvtData(T evtData) {
        this.evtData = evtData;
    }
}
