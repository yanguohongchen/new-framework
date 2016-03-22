package com.cagtc.framework.exception;

/**
 * 业务异常
 * 
 * @author sea
 *
 */
public class BusinessException extends Exception
{

	private static final long serialVersionUID = 1L;

	private int code;

	public BusinessException()
	{
	}

	public BusinessException(String message)
	{
		super(message);
	}

	public BusinessException(Throwable cause)
	{
		super(cause);
	}

	public BusinessException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public BusinessException(EnumVal errorCode)
	{
		this(errorCode.getMsg());
		this.code = errorCode.getCode();
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

}