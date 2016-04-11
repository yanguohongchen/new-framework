package com.cagtc.framework.service;

import com.cagtc.framework.auth.SessionData;

public interface ITokenService {

	/**
	 * 创建会话数据
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	SessionData createSessionData(String username);

	/**
	 * 获取会话数据
	 * 
	 * @param username
	 * @return
	 */
	SessionData getSessionData(String username);

	/**
	 * 从数据库中获取用户信息
	 * @param username
	 * @return
	 */
	SessionData getUserInfoByDB(String username);

	/**
	 * 登录认证
	 * @param username
	 * @param password
	 */
	void login(String username, String password);

}
