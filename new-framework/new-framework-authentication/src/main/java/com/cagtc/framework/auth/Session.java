package com.cagtc.framework.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.cagtc.framework.exception.DeniedException;

public class Session
{

	private final static Logger logger = LoggerFactory.getLogger(Session.class.getName());

	protected Map<String, String[]> params = new HashMap<>();

	public Session(HttpServletRequest request, HttpServletResponse response, Object handler) 
	{
		if (handler instanceof HandlerMethod)
		{
			HandlerMethod handler2 = (HandlerMethod) handler;
			FireAuthority fireAuthority = handler2.getMethodAnnotation(FireAuthority.class);

			if (fireAuthority != null)
			{
				if (fireAuthority.loginStatus().equals(LoginStatus.LOGIN))
				{
					loginAuth(request);
				} else if (fireAuthority.role() != null)
				{
					// permissionAuth(request, fireAuthority.role());
				} else if (fireAuthority.loginStatus().equals(LoginStatus.NO_LOGIN))
				{
					logger.info("认证通过！");
				} else
				{
					throw new DeniedException("权限验证失败！");
				}
			}
		}
	}

	/*
	 * private void permissionAuth(HttpServletRequest request, Role[] roles) {
	 * String token = request.getParameter("token"); String username =
	 * token.split(":")[0]; IUserService userService =
	 * SpringUtils.getBean("userService"); UserEntity user =
	 * userService.getUserByUserName(username); String roleString =
	 * user.getRoles(); String[] roleIdArr = roleString.split(","); boolean flag
	 * = false; for (Role role : roles) { for (String roleId : roleIdArr) { if
	 * (String.valueOf(role.getRoleId()).equals(roleId)) { flag = true; } } } if
	 * (flag) { throw new DeniedException("角色不匹配！"); } }
	 */

	/**
	 * 登录验证
	 * 
	 * @param request
	 * @throws IOException 
	 */
	private void loginAuth(HttpServletRequest request)
	{
		TokenUtil.clientValidator(request);
		String token = request.getParameter("token");
		if (token == null || token.equals(""))
		{
			throw new DeniedException("对不起，请先登录！");
		} else
		{
			TokenUtil.validateToken(token);
		}
	}

}
