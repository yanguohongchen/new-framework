package com.cagtc.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cagtc.framework.auth.RedisClusterUtil;
import com.cagtc.framework.auth.TokenUtil;

@Service
public class TokenService implements ITokenService
{
	@Autowired
	private RedisClusterUtil redisClusterUtil;

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
		redisClusterUtil.getJc().set(TOKENKEY + username, tokenBuilder.toString());
		return tokenBuilder.toString();
	}

	@Override
	public String getToken(String username)
	{
		return redisClusterUtil.getJc().get(TOKENKEY + username);
	}
}
