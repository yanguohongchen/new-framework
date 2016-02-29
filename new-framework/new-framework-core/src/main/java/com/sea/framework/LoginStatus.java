package com.sea.framework;

public enum LoginStatus
{

	/**
	 * 无需登录
	 */
	NO_LOGIN("no_login"),
	/**
	 * 登录
	 */
	LOGIN("login");

	private String loginStatus;

	private LoginStatus(String loginStatus)
	{
		this.loginStatus = loginStatus;
	}

	@Override
	public String toString()
	{
		return loginStatus;
	}

}
