package com.douaiwan.eliminate.session;

import com.douaiwan.eliminate.pojo.data.ServerData;
import com.douaiwan.eliminate.session.interfaces.ISession;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class SessionPool {
	
	private static Hashtable<ChannelHandlerContext, ISession> pool = new Hashtable<ChannelHandlerContext, ISession>();
	private static Hashtable<Long, ISession> online = new Hashtable<Long, ISession>();
	//private static Hashtable<Long, LoginAccountInfo> outlineInfo = new Hashtable<Long, LoginAccountInfo>();
	//private static Hashtable<Long, LoginAccountInfo> onlineInfo = new Hashtable<Long, LoginAccountInfo>();
	
	public static boolean getOnline(long id)
	{
		return online.containsKey( id );
	}
	
	public static ISession getOnlineSession(long id){
		if( online.containsKey( id ) ) {
			//return online.get( id );
			List<ServerData> l = new ArrayList<>();
			List<Integer> transactionsIds = l.parallelStream().
					filter(t -> t.getServerID() == 100 ).
					sorted(comparing(ServerData::getServerID).reversed()).
					map(ServerData::getServerID).
					collect(toList());
		}
		return null;
	}

	public static Session BuildSession(ChannelHandlerContext value){
		return new Session().setSocketChanel(value);
	}
	
	public static Hashtable<Long, ISession> getAll(){
		return online;
	}
	
	public static void setOnline(long id,ISession session){
		online.put( id , session);
	}
	
	public static ISession removeOnline( long id ){
		if( online.containsKey(id) ) {
			return online.remove(id);
		}
		return null;
	}
	
	public static void registerSession(ChannelHandlerContext context,ISession session){
		if( context == null ) {
			System.out.println("Session is null");
		}
		pool.put(context,session);
	}

	public static ISession getSession(ChannelHandlerContext context){
		if(pool.containsKey(context)){
			return pool.get(context);
		}
		return null;
	}
	
	public static ISession removeSession(ChannelHandlerContext context){
		if( pool.containsKey(context) && context != null ) {
			ISession session = pool.remove( context );
			System.out.println("从pool删除");
			return session;
		}
		return null;
	}
	
	public static boolean contains(ChannelHandlerContext context){
		return pool.containsKey( context );
	}
}
