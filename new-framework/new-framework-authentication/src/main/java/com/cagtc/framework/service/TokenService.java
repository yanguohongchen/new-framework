package com.cagtc.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cagtc.framework.auth.SessionData;
import com.cagtc.framework.auth.TokenUtil;
import com.cagtc.framework.redis.JedisPoolUtil;
import com.google.gson.Gson;

@Service
public class TokenService implements ITokenService {

	@Autowired
	private JedisPoolUtil jedisPoolUtil;

	@Autowired
	private IUserVoService userVoService;

	private final String TOKENKEY = "AUTH:USERCENTER:";

	@Override
	public SessionData createSessionData(String username) {
		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(username);
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtil.computeSignature(username, expires));
		SessionData sessionData = getUserInfoByDB(username);
		sessionData.setAccessToken(tokenBuilder.toString());
		try (Jedis jedis = jedisPoolUtil.getJedis()) {
			jedis.set(TOKENKEY + username, new Gson().toJson(sessionData).toString());
			jedis.expire(TOKENKEY + username, 60 * 60 * 4);
		}
		return sessionData;
	}

	@Override
	public void login(String username, String password) {
		userVoService.loginValid(username, password);
	}

	/**
	 * 获取用户并验证用户信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public SessionData getUserInfoByDB(String username) {
		// 登录验证

		SessionData user = new SessionData();
		UserVo userVo = userVoService.getUserVoByUsername(username);
		user.setUsername(username);
		user.setUserid(userVo.getUserid());
		user.setPassword(userVo.getPasword());
		return user;
	}

	@Override
	public SessionData getSessionData(String username) {
		try (Jedis jedis = jedisPoolUtil.getJedis()) {
			return new Gson().fromJson(jedis.get(TOKENKEY + username), SessionData.class);
		}
	}

}
