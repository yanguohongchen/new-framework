package com.cagtc.framework.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商城pc端认证
 * 
 * @author Administrator
 *
 */
public interface IMallPcAuthService {

	/**
	 * 授权认证，如果认证成功则生成token。
	 * @param request
	 * @param response
	 */
	public void auth(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 认证成功
	 * @param request
	 * @param response
	 */
	public void authSuccess(HttpServletRequest request, HttpServletResponse response);

}
