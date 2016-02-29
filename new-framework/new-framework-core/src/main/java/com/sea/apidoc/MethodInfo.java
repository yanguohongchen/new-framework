package com.sea.apidoc;

import java.util.List;

public class MethodInfo
{

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 匹配名
	 */
	private String mapperName;

	/**
	 * 简介
	 */
	private String summary;

	/**
	 * 方法所属模块
	 */
	private String module;

	/**
	 * 返回数据 json格式
	 */
	private Object data;

	/**
	 * 返回类型
	 */
	private String returnType;

	/**
	 * 参数
	 */
	private List<Param> params;

	/**
	 * 搜索索引（）
	 */
	private String searchKey;
	
	
	//异常

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public List<Param> getParams()
	{
		return params;
	}

	public void setParams(List<Param> params)
	{
		this.params = params;
	}

	public String getSearchKey()
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public String getMapperName()
	{
		return mapperName;
	}

	public void setMapperName(String mapperName)
	{
		this.mapperName = mapperName;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(String module)
	{
		this.module = module;
	}

	public String getReturnType()
	{
		return returnType;
	}

	public void setReturnType(String returnType)
	{
		this.returnType = returnType;
	}

}
