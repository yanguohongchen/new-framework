package com.cagtc.framework.auth;

public class AppContext
{
	private static ThreadLocal<Session> sessionPools = new ThreadLocal<Session>();

	public static void putSession(Session session)
	{
		sessionPools.set(session);
	}

	public static Session getSession()
	{
		return sessionPools.get();
	}

}
