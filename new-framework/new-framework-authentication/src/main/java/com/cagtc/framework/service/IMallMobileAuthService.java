package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商城手机端认证
 * 
 * @author Administrator
 *
 */
public interface IMallMobileAuthService {
	
	/**
	 * 授权认证
	 * 
	 * @param request
	 * @param response
	 */
	public void auth(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 认证成功
	 * 
	 * @param request
	 * @param response
	 */
	public void loginSuccess(HttpServletRequest request, HttpServletResponse response);

}
