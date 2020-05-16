package com.douaiwan.eliminate.session.interfaces;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.channels.AsynchronousChannelGroup;

public abstract interface ISession  {
	ChannelHandlerContext getChannel();
	String Address();
	void setPlayer(Object obj);
	Object getPlayer();
	void write(ByteBuf arg0, int id);
	ISession setSocketChanel(ChannelHandlerContext value);
	void Clear();
	void Dispose();
	void toCash();
	void setChannel(ChannelHandlerContext value);
	void send();
	void writeAndSend(ByteBuf arg0, int id);
	void setMustKick(boolean blue);
	boolean getMustKick();
	long getHeartTime();
	void setHeartTime(long heartTime);
}
