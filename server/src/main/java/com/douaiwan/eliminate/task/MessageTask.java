package com.douaiwan.eliminate.task;

import com.douaiwan.eliminate.session.interfaces.ISession;
import io.netty.buffer.ByteBuf;

public class MessageTask {
    private int head;
    private ByteBuf body;
    private ISession session;

    public MessageTask(int value_1, ByteBuf value_2, ISession value_3) {
        this.head = value_1;
        this.body = value_2;
        this.session = value_3;
    }

    public ISession getSession() {
        return this.session;
    }

    public void setSession(ISession session) {
        this.session = session;
    }

    public int getHead() {
        return this.head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public ByteBuf getBody() {
        return this.body;
    }

    public void setBody(ByteBuf body) {
        this.body = body;
    }
}
