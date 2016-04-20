package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cagtc.framework.auth.SessionData;

/**
 * 商城手机端认证
 * 
 * @author Administrator
 *
 */
public interface IMallMobileAuthService
{

	/**
	 * 授权认证
	 * 
	 * @param request
	 * @param response
	 */
	public SessionData auth(HttpServletRequest request, HttpServletResponse response, String channel);

	/**
	 * 认证成功，仿造商城写回cookie到客户端。
	 * 
	 * @param request
	 * @param response
	 */
	public void tokenLoginSuccess(HttpServletRequest request, HttpServletResponse response, String username);
	
	

}
