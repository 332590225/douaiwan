package com.douaiwan.eliminate.session;

import com.douaiwan.eliminate.session.interfaces.ISession;
import io.netty.channel.ChannelHandlerContext;

import java.util.Hashtable;

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
			return online.get( id );
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
		if( online.containsKey(id) ) 
		{
			return online.remove(id);
		}
		return null;
	}
	
	public static void registerSession(ChannelHandlerContext context,ISession session){
		if( context == null )
		{
			System.out.println("Session is null ");
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
		
		if( pool.containsKey(context) && context != null )
		{
			ISession session = pool.remove( context );
			System.out.println("从pool删除");
			
			return session;
		}
		return null;
	}
	
	public static boolean contains(ChannelHandlerContext context){
		return pool.containsKey(context);
	}

	/*
	public static boolean containsOutLine(long id){
		return outlineInfo.containsKey(id);
	}


	public static LoginAccountInfo getOutline(long id) {
		return outlineInfo.get(id);
	}
	
	public static Hashtable<Long, LoginAccountInfo> getOutlineAll() {
		return outlineInfo;
	}

	public static void setOutline(LoginAccountInfo loginAc) {
		outlineInfo.put( loginAc.getUniqueId(), loginAc);
		removeOnlineInfo( loginAc.getUniqueId() );
	}
	
	public static void removeOutline(long id) {
		outlineInfo.remove( id );
	}

	public static boolean containsonLine(long id){
		return onlineInfo.containsKey(id);
	}
	
	public static LoginAccountInfo getOnlineInfo(long id) 
	{
		if( onlineInfo.containsKey( id ) ) 
		{
			return onlineInfo.get(id);
		}
		return null;
	}

	
	
	public static Hashtable<Long, LoginAccountInfo> getOnlineAll() {
		return onlineInfo;
	}

	public static void setOnline(LoginAccountInfo loginAc) {
		onlineInfo.put( loginAc.getUniqueId() , loginAc);
		removeOutline( loginAc.getUniqueId() );
	}
	
	private static void removeOnlineInfo( long id ) {
		onlineInfo.remove( id );
	}
	*/
}
