package com.douaiwan.eliminate.dispathers;

import com.douaiwan.eliminate.dispathers.base.BaseDispather;
import com.douaiwan.eliminate.task.MessageTask;

public class MessageDispather extends BaseDispather<MessageTask> {
    private static MessageDispather insit = null;
    public static MessageDispather getInstance() {
        if (insit == null) {
            insit = new MessageDispather();
        }
        return insit;
    }
}
