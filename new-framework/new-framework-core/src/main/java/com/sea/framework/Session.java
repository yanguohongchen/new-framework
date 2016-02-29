package com.sea.framework;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import com.sea.exception.DeniedException;

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
					// /TODO:获取用户信息，获取权限，比对。配置角色名字
					logger.info("认证通过！");
					permissionAuth(request, fireAuthority.role());
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

	private void permissionAuth(HttpServletRequest request, Role[] roles)
	{
		// TODO:获取用户角色即可
//		String sid = request.getParameter("sid");

	}

	private void loginAuth(HttpServletRequest request)
	{
		TokenUtil.clientValidator(request);
		String token = request.getHeader("token");
		if (token == null || token.equals(""))
		{
			throw new DeniedException("对不起，请先登录！");
		} else
		{
			User user = new User();
			user.setUsername(request.getHeader("username"));
			TokenUtil.validateToken(token, user);
		}
	}
}
