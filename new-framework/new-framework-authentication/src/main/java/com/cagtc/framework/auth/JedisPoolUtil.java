package com.cagtc.framework.auth;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolUtil
{
	private JedisPool jedisPool;

	public JedisPoolUtil(String hostport, int maxIdle, int maxTotal)
	{
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMaxTotal(maxTotal);
		genericObjectPoolConfig.setTestOnBorrow(false);
		String host = hostport.split(":")[0];
		int port = Integer.parseInt(hostport.split(":")[1]);
		jedisPool = new JedisPool(genericObjectPoolConfig, host, port);
	}

	public Jedis getJedis()
	{
		return jedisPool.getResource();
	}

	public void destroy()
	{
		jedisPool.destroy();
	}

}
