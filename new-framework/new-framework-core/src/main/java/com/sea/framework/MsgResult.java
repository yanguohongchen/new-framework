package com.sea.framework;

public class MsgResult
{

	public MsgResult(String status, String msg)
	{
		this(null, status, msg);
	}

	public MsgResult()
	{
	}

	public MsgResult(Object data)
	{
		this.data = data;
	}

	public MsgResult(Object data, String status, String msg)
	{
		this.data = data;
		this.status = status;
		this.msg = msg;
	}

	private String status = Status.OK.toString();

	private String msg = "success";

	protected Object data;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
}
