package com.douaiwan.eliminate.pojo.group;

import com.douaiwan.eliminate.pojo.data.ServerData;

import java.util.HashMap;
import java.util.Map;

public class Group {
    private Map<Integer, ServerData> group = new HashMap();

    public Map<Integer, ServerData> getGroup() {
        return group;
    }

    public void setGroup(Map<Integer, ServerData> group) {
        this.group = group;
    }
}
