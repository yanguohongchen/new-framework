package com.sea.framework;

public enum Role
{

	/**
	 * 超级管理员
	 */
	ADMIN(1, "admin"),
	/**
	 * 普通用户
	 */
	CONSUMER(2, "consumer");

	public static Role getRole(int roleId)
	{
		switch (roleId)
		{
		case 1:

			return Role.ADMIN;
		case 2:
			return Role.CONSUMER;
		default:
			return null;
		}
	}

	private int roleId;
	private String roleName;

	private Role(int roleId, String roleName)
	{
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public int getRoleId()
	{
		return this.roleId;
	}

	public String getRoleName()
	{
		return this.roleName;
	}

	@Override
	public String toString()
	{
		return String.valueOf(this.roleId);
	}

}
