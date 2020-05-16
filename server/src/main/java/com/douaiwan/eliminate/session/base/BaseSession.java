package com.douaiwan.eliminate.session.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.UUID;
import java.util.Vector;

public abstract class BaseSession {
	
	protected String ID = UUID.randomUUID().toString();
	protected long BaseID = 0;
	public ByteBuf bytebuffer = null;
	public ByteBuf cashbuffer = null;
	protected ChannelHandlerContext Channel = null;
	protected Object user;
	protected Hashtable<String,Object> Attributes = new Hashtable<String,Object>();
	protected boolean mustKick = false;
	protected long heartTime = System.currentTimeMillis();

	/*
	这些参数移动到用户对象身上
	protected long rewardTime = System.currentTimeMillis();
	protected long saveTime = System.currentTimeMillis();
	* */
}