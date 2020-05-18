package com.douaiwan.eliminate.handlers;

import com.douaiwan.eliminate.server.HttpServer;
import com.douaiwan.eliminate.server.Server;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

import java.util.stream.Stream;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> implements ChannelHandler {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if (!request.decoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade")))) {
			System.out.println( "http请求:"+request.uri() );
			HttpServer.getInstance().DecordRequest( ctx , request);
		}else{
			WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:"+ Server.SERVER_PORT, null, false);
			WebSocketServerHandshaker handshaker = wsFactory.newHandshaker( request );

			if( null == handshaker ) {
				System.out.println( "握手失败:"+request.uri() );
				WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
			} else{
				handshaker.handshake( ctx.channel() , request);
			}
		}
	}
}
