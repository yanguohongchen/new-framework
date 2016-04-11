package com.cagtc.framework.service;

import com.cagtc.framework.exception.BusinessException;

public interface IUserVoService {

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	public UserVo getUserVoByUsername(String username);

	/**
	 * 验证用户账号信息是否正确
	 * 
	 * @param username
	 * @param password
	 * @throws BusinessException
	 */
	public void loginValid(String username, String password);
}
