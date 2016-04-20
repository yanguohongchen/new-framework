package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cagtc.framework.auth.SessionData;

public abstract class AbstractMallMobileAuthService implements IMallMobileAuthService {

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private ICookieService cookieService;

	@Override
	public SessionData auth(HttpServletRequest request, HttpServletResponse response, String channel) {
		String username = readUserInfoFromCookie(request);
		if (username != null && !username.equals("")) {
			SessionData sessionData = tokenService.createSessionData(username);
			String token = sessionData.getAccessToken();
			cookieService.saveCookie(response, token);
			return sessionData;
		}
		return new SessionData();
	}

	/**
	 * 商城认证，从cookie中解析出用户名。
	 * 
	 * @return
	 */
	public abstract String readUserInfoFromCookie(HttpServletRequest request);

}
