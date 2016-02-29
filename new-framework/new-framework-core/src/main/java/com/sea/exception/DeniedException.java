package com.sea.exception;

/**
 * 业务异常
 * 
 * @author sea
 *
 */
public class DeniedException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public DeniedException()
	{
	}

	public DeniedException(String message)
	{
		super(message);
	}

	public DeniedException(Throwable cause)
	{
		super(cause);
	}

	public DeniedException(String message, Throwable cause)
	{
		super(message, cause);
	}

}