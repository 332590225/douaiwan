package com.douaiwan.eliminate.handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class AcceptHandler extends ChannelInitializer<SocketChannel> implements ChannelHandler {

	@Override
	protected void initChannel(SocketChannel newConnect) throws Exception 
	{
		ChannelPipeline pipeline = newConnect.pipeline();
        pipeline.addLast( new HttpServerCodec() ); 
        pipeline.addLast( new HttpObjectAggregator( 4*1024 ) );
        pipeline.addLast( new ChunkedWriteHandler() );
        pipeline.addLast( new HttpRequestHandler() );
        pipeline.addLast( new WebSocketBusinsHandler() );
	}
}
