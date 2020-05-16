package com.douaiwan.eliminate.modules;

import com.douaiwan.eliminate.config.ServerConfig;
import com.douaiwan.eliminate.dispathers.EventDispather;
import com.douaiwan.eliminate.dispathers.interfaces.IListener;
import com.douaiwan.eliminate.pojo.data.ServerData;
import com.douaiwan.eliminate.pojo.group.Group;
import com.douaiwan.eliminate.task.EventTask;
import com.google.inject.Inject;

public class GroupModule implements IListener<EventTask> {

    private final Group group = new Group();

    @Inject
    public GroupModule() {
        EventDispather.getInstance().addEventListener(this);
    }

    private void initGroup(){
        String[] groups = ServerConfig.getInstance().properties.getProperty("group").split(",");
        for ( String server : groups ) {
            group.getGroup().put(Integer.parseInt(server),new ServerData(Integer.parseInt(server)) );
        }
    }

    @Override
    public void call(EventTask eventTask) {

    }
}
