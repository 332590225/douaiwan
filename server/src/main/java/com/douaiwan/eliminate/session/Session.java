package com.douaiwan.eliminate.session;

import com.douaiwan.eliminate.session.base.BaseSession;
import com.douaiwan.eliminate.session.interfaces.ISession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.nio.channels.AsynchronousChannelGroup;

public class Session extends BaseSession implements ISession {
	
	
	@Override
	public Session setSocketChanel(ChannelHandlerContext value) {
		Channel = value;
		init();
		return this;
	}
	
	private void init() {
		bytebuffer = Unpooled.buffer();
		cashbuffer = Unpooled.buffer();
	}

	@Override
	public void Dispose() {
		Channel.close();
		Clear();
	}


	@Override
	public void setPlayer(Object obj) 
	{
		this.user = obj;
	}

	@Override
	public Object getPlayer() 
	{
		return user;
	}


	@Override
	public String Address() {
		java.net.InetSocketAddress insocket = (java.net.InetSocketAddress) Channel.channel().remoteAddress();
		return insocket.getAddress().getHostAddress();
	}

	@Override
	public void Clear() {
		Channel = null;
		bytebuffer = null;
		Attributes.clear();
	}

	
	@Override
	public void toCash() {
		ByteBuf newBuf = Unpooled.buffer();
		newBuf.readBytes( bytebuffer );
		bytebuffer.clear();
		bytebuffer = newBuf;
	}


	@Override
	public ChannelHandlerContext getChannel() 
	{
		return Channel;
	}
	
	@Override
	public void setChannel(ChannelHandlerContext value) 
	{
		Channel = value;
	}



	@Override
	public synchronized void send() {
	    if( Channel != null && cashbuffer.writerIndex() > 0 ) {
	        ByteBuf buf = Unpooled.buffer();
	        buf.writeBytes(cashbuffer);
				
	        cashbuffer.clear();
	        Channel.writeAndFlush( new BinaryWebSocketFrame( buf ) );
	    }
		cashbuffer.clear();
	}

	@Override
	public synchronized void writeAndSend(ByteBuf bytes, int id) {
		cashbuffer.writeInt(bytes.writerIndex() + 8);
		cashbuffer.writeInt(id);
		cashbuffer.writeBytes(bytes);
		send();
	}
	
	@Override
	public synchronized void write(ByteBuf bytes , int id) {
		cashbuffer.writeInt(bytes.writerIndex() + 8);
		cashbuffer.writeInt(id);
		cashbuffer.writeBytes(bytes);
	}

	@Override
	public void setMustKick(boolean blue) 
	{
		this.mustKick = blue;
	}

	@Override
	public boolean getMustKick() {
		return mustKick;
	}

    @Override
    public long getHeartTime() {
        return heartTime;
    }

    @Override
    public void setHeartTime( long heartTime ) {
        this.heartTime = heartTime;
    }

}
