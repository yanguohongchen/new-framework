package com.sea.apidoc;

import java.util.ArrayList;
import java.util.List;

public class Param
{

	/**
	 * 参数类型
	 */
	private String paramType;

	/**
	 * 参数名
	 */
	private String paramName;

	/**
	 * 描述
	 */
	private String summary;
	
	
	private String defaultValue;

	/**
	 * 格式
	 */
	private List<String> formats = new ArrayList<String>();


	public String getParamType()
	{
		return paramType;
	}

	public void setParamType(String paramType)
	{
		this.paramType = paramType;
	}

	public String getParamName()
	{
		return paramName;
	}

	public void setParamName(String paramName)
	{
		this.paramName = paramName;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}


	public List<String> getFormats()
	{
		return formats;
	}

	public void setFormats(List<String> formats)
	{
		this.formats = formats;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	
	

}
