package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMallMobileAuthService implements IMallMobileAuthService {

	@Autowired
	private ITokenService tokenService;

	@Override
	public void auth(HttpServletRequest request, HttpServletResponse response) {
		String username = login();
		tokenService.createSessionData(username);
	}

	/**
	 * 商城认证并返回用户名
	 * 
	 * @return
	 */
	public abstract String login();

}
