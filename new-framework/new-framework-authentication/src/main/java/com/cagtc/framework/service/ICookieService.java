package com.cagtc.framework.service;

import javax.servlet.http.HttpServletResponse;

public interface ICookieService {

	/**
	 * 登录秒杀系统，写入cookie到客户端。
	 * 
	 * @param response
	 */
	public void saveCookie(HttpServletResponse response, String token);

}
