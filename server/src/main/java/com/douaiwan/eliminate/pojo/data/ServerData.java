package com.douaiwan.eliminate.pojo.data;

import java.util.HashMap;
import java.util.Map;

public class ServerData {
    private int serverID = 0;
    private transient Map<Long,Object> online = new HashMap<>();

    public ServerData(int serverID) {
        this.serverID = serverID;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public Map<Long, Object> getOnline() {
        return online;
    }

    public void setOnline(Map<Long, Object> online) {
        this.online = online;
    }
}
