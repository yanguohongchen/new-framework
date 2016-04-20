package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cagtc.framework.auth.SessionData;

public abstract class AbstractMallPcAuthService implements IMallPcAuthService {

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private ICookieService cookieService;

	@Override
	public SessionData auth(HttpServletRequest request, HttpServletResponse response) {
		String username = readUserInfoFromCookie(request);
		if (username != null && !username.equals("")) {
			SessionData tokenData = tokenService.createSessionData(username);
			String token = tokenData.getAccessToken();
			cookieService.saveCookie(response, token);
			return tokenData;
		}
		return new SessionData();
	}

	/**
	 * 商城认证并返回用户名
	 * 
	 * @return
	 */
	public abstract String readUserInfoFromCookie(HttpServletRequest request);

}
