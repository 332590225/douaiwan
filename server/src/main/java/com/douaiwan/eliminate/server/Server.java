package com.douaiwan.eliminate.server;

import com.douaiwan.eliminate.config.ServerConfig;
import com.douaiwan.eliminate.handlers.AcceptHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.lang.reflect.Array;
import java.util.Properties;

public class Server {
    public static int SERVER_PORT = 4234; //服务器端口4234
    private int WaitQueueSize = 1024; //等待连接的最大长度
    private int WorkThreadSize = 1; //工作线程的最大长度

    public void start(){
        SERVER_PORT = Integer.parseInt( ServerConfig.getInstance().properties.get("SERVER_PORT").toString());
        WorkThreadSize = Integer.parseInt( ServerConfig.getInstance().properties.get("WORK_THREAD_SIZE").toString() );

        EventLoopGroup bossGroup = new NioEventLoopGroup( 1);
        EventLoopGroup workGroup = new NioEventLoopGroup( WorkThreadSize );

        try{
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group( bossGroup , workGroup ).channel( NioServerSocketChannel.class ).childHandler( new AcceptHandler() )
                    .option( ChannelOption.SO_BACKLOG , WaitQueueSize ).childOption( ChannelOption.SO_KEEPALIVE , true );

            ChannelFuture future = bootStrap.bind( SERVER_PORT ).sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
