package com.cagtc.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cagtc.framework.auth.JedisPoolUtil;
import com.cagtc.framework.auth.TokenUtil;

@Service
public class TokenService implements ITokenService
{

	@Autowired
	private JedisPoolUtil jedisPoolUtil;

	private final String TOKENKEY = "AUTH:USERCENTER:";

	@Override
	public String createToken(String username)
	{
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(username);
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtil.computeSignature(username, expires));
		try (Jedis jedis = jedisPoolUtil.getJedis())
		{
			jedis.exists(TOKENKEY + username, tokenBuilder.toString());
		}
		return tokenBuilder.toString();
	}

	@Override
	public String getToken(String username)
	{
		try (Jedis jedis = jedisPoolUtil.getJedis())
		{
			return jedis.get(TOKENKEY + username);
		}
	}
}
