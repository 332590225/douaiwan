package com.douaiwan.eliminate.dispathers;


import com.douaiwan.eliminate.dispathers.base.BaseDispather;
import com.douaiwan.eliminate.task.EventTask;

public class EventDispather extends BaseDispather<EventTask> {
    private static EventDispather insit = null;
    public static EventDispather getInstance() {
        if (insit == null) {
            insit = new EventDispather();
        }
        return insit;
    }
}
