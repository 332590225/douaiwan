package com.douaiwan.eliminate.server;

import com.douaiwan.eliminate.commands.TestCommand;
import com.douaiwan.eliminate.commands.interfaces.ICommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class HttpServer {

	private static final String TEST = "TEST"; //测试
	private static final String RECHARGE = "recharge"; //充值接口
	private static final String ONLINE_DETAIL = "online_detail"; //在线查询
	private static final String RETAIN_DETAIL = "retain_detail"; //留存
	private static final String Task_DETAIL = "task_detail"; //任务
	private static final String DIS_DETAIL = "distribution_detail"; //
	private static final String RESGISTER_DETAIL = "resgister_detail"; //注册
	private static final String NOTICE_DETAIL = "notice_detail"; //公告
	private static final String COMMAND_MAIL = "command_mail"; //邮件
	private static final String STATISTICS_DETAIL = "Statistics_detail"; //邮件
	
	/* 新的后台接口通道*/
	private static final String UserOnline = "UserOnline";
	private static final String Online = "online";
	private static final String RoleList = "roleList";
	private static final String RoleInfo = "roleInfo";
	private static final String PluginQuery = "pluginQuery";
	private static final String GmCommand = "gmCommand";
	private static final String Anexcuse = "anexcuse"; //禁言
	private static final String Ban = "ban"; //封停
	private static final String Kick = "kick"; //踢人
	
	
	private static HttpServer insit = null;
	private HashMap<String, Class<?>> commands = new HashMap<String, Class<?>>();
	
	public static HttpServer getInstance() {
		if( insit == null ) {
			insit = new HttpServer();
		}
		return insit;
	}
	
	protected HttpServer() {
		commands.put( TEST,  TestCommand.class );
	}
	
	public void DecordRequest( ChannelHandlerContext ctx, FullHttpRequest request ) {
		//远程RUL调用请求
		if( request.uri().length() <= 1 ) {
			return;
		}
		
		if( request.method() == HttpMethod.GET) {
			return;
		}
		
		String command = request.uri().substring( 1 , request.uri().length() );
		String parameter = "";
		
		if( request.content().writerIndex() > 0 ) {
			parameter = readString( request.content() );
		}
		
		if( command.indexOf("?") != - 1 ) {
			command = command.substring(0 ,command.indexOf("?"));
		}

		if( commands.containsKey( command ) ) {
			try {
                ICommand icommand = (ICommand) commands.get( command ).newInstance();
				parameter = URLDecoder.decode(parameter, "utf8");
				String response = icommand.doCommand(parameter);
				if( response != null ) {
					writeResponse( ctx , response );
				}
			} catch ( Exception  e) {
				e.printStackTrace();
			} catch ( Error e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
     * http返回响应数据
     * @param channel
     */
	public void writeResponse( ChannelHandlerContext channel ,String responseContent) {
        // 创建返回对象
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.buffer() );

        byte[] strByte;
		try {
			strByte = responseContent.getBytes("UTF-8");
			response.headers().set( HttpHeaderNames.CONTENT_TYPE,  "text/html" );
			response.headers().set( HttpHeaderNames.CONTENT_LENGTH,  strByte.length );
		    response.content().writeBytes( strByte );
		    
		    channel.write( response );
		    channel.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
	
	 public static String readString( ByteBuf buf ) {
		try {
			byte[] bytes = new byte[buf.writerIndex()];
			buf.readBytes(bytes);
			return new String(bytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	 }
}
