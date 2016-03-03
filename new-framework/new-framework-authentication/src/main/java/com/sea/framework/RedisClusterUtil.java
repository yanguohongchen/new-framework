package com.sea.framework;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterUtil
{
	private Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

	private JedisCluster jc;

	public RedisClusterUtil()
	{
		getRedisInstances();
		initRedisPool();
	}


	private void getRedisInstances()
	{
		jedisClusterNodes.add(new HostAndPort("192.168.140.120", 6380));
		jedisClusterNodes.add(new HostAndPort("192.168.140.120", 6381));
		jedisClusterNodes.add(new HostAndPort("192.168.140.120", 6382));
	}

	public void initRedisPool()
	{
		jc = new JedisCluster(jedisClusterNodes);
	}

	public void destoryRedisPool() throws IOException
	{
		jc.close();
	}

	public JedisCluster getJc()
	{
		return jc;
	}

	public static void main(String[] args)
	{
		RedisClusterUtil redisCluserUtil = new RedisClusterUtil();
		redisCluserUtil.initRedisPool();
		JedisCluster jc = redisCluserUtil.getJc();
		jc.set("foo", "bar");
		jc.expire("foo", 60 * 3);
		String value = jc.get("foo");
		System.out.println(value);
		try
		{
			redisCluserUtil.destoryRedisPool();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
