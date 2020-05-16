package com.douaiwan.eliminate.handlers;

import com.douaiwan.eliminate.dispathers.MessageDispather;
import com.douaiwan.eliminate.session.Session;
import com.douaiwan.eliminate.session.SessionPool;
import com.douaiwan.eliminate.session.interfaces.ISession;
import com.douaiwan.eliminate.task.MessageTask;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class WebSocketBusinsHandler extends SimpleChannelInboundHandler<WebSocketFrame> implements ChannelHandler {
	
	private static final int minLength = 12; //最小有效数据长度
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) throws Exception {
		
	}

	@Override
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		if( !channels.contains( arg0.channel() ) ) {
			channels.add(arg0.channel());

			ISession session = SessionPool.BuildSession(arg0);
			SessionPool.registerSession(arg0, session);
			session.setChannel( arg0 );
			System.out.println("没有这个连接  加入队列");
		}
	}

	@Override
	public void handlerRemoved( ChannelHandlerContext context ) throws Exception {
		ISession session = SessionPool.getSession( context );
		if( session != null ) {
			session.setHeartTime(0);
			System.out.println("客户端与服务器段断开连接");
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
		try {
			Validation( msg.content() , ctx );
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	 public void Validation(ByteBuf msg, ChannelHandlerContext ctx) {
		System.out.println("接收到消息 长度:"+msg.readableBytes());
		Session session = (Session) SessionPool.getSession( ctx );
		
		if( session == null ) {
			session = SessionPool.BuildSession(ctx);
			SessionPool.registerSession( ctx , session);
			session.setChannel(ctx);
			//ServerLog.DoLog( " Session不存在 " );
			return;
		}

		session.bytebuffer.writeBytes(msg);
		msg.clear();
		//ServerLog.DoLog("合并缓存后剩余长度:"+attachment.bytebuffer.writerIndex());
		if( session.bytebuffer.writerIndex() < minLength ) {
			System.out.println("可用长度不够 缓存");
            session.bytebuffer.readerIndex(0);
		}else {
			if (session.bytebuffer.writerIndex() >= minLength) {
				boolean flag = true;
				while (flag) {
					//获取整个消息的长度 （  这是除开消息长度本身的长度  ）
					int MsgLength = session.bytebuffer.readInt();
					//如果头部表示后面的长度为0  那么直接返回 这消息作废
					if (MsgLength <= 0) {
						flag = false;
                        session.bytebuffer.clear();
						return;
					}

					//我需要的字节数 > 现在有的字节数 断包了
					if (MsgLength > session.bytebuffer.writerIndex() - 4) {
                        session.bytebuffer.readerIndex(0);
						flag = false;
						return;
					}

					int MsgHead = session.bytebuffer.readInt();
					ByteBuf BusinsBuf = session.bytebuffer.readBytes(MsgLength - (minLength - 4));

					int newLength = session.bytebuffer.writerIndex() - session.bytebuffer.readerIndex();
					if (newLength >= minLength) {
						flag = true;
					} else {
						flag = false;
						if (session.bytebuffer.writerIndex() == session.bytebuffer.readerIndex()) {
                            session.bytebuffer.clear();
						} else {
                            session.toCash();
						}
					}
					MessageDispather.getInstance().dispatherEvent( new MessageTask(MsgHead, BusinsBuf, session));
				}
			}
		}
	}
}
