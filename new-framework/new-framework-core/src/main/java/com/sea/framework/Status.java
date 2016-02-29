package com.sea.framework;

public enum Status
{

	/**
	 * 成功
	 */
	OK("200"),
	/**
	 * 业务异常
	 */
	BusinessException("400"),

	/**
	 * 权限异常
	 */
	DeniedException("401"),

	/**
	 * 服务端异常
	 */
	ServerException("500");

	private String code;

	private Status(String code)
	{
		this.code = code;
	}

	@Override
	public String toString()
	{
		return code;
	}

	public static void main(String[] args)
	{
		System.out.println(Status.OK.toString());
	}

}
