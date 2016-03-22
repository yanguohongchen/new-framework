package com.cagtc.framework.auth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterUtil
{
	private Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

	private JedisCluster jc;

	public RedisClusterUtil(String redisClusterString, int maxIdle, int maxTotal)
	{
		getRedisInstances(redisClusterString);
		initRedisPool(maxIdle, maxTotal);
	}

	private void getRedisInstances(String redisClusterString)
	{
		String[] redisArr = redisClusterString.split(",");
		for (String redis : redisArr)
		{
			String ip = redis.split(",")[0];
			int port = Integer.parseInt(redis.split(",")[1]);
			jedisClusterNodes.add(new HostAndPort(ip, port));
		}
	}

	public void initRedisPool(int maxIdle, int maxTotal)
	{
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMaxTotal(maxTotal);
		genericObjectPoolConfig.setTestOnBorrow(false);
		jc = new JedisCluster(jedisClusterNodes, genericObjectPoolConfig);
	}

	public void destoryRedisPool() throws IOException
	{
		jc.close();
	}

	public JedisCluster getJc()
	{
		return jc;
	}
}
