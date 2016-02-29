package com.sea.framework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 性能拦截拦截器
 * 
 * @author sea
 * 
 */
public class PerformanceInterceptor implements HandlerInterceptor
{
	private final static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class.getName());

	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");

	/**
	 * 是否开启性能监控
	 */
	private boolean performance = true;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (performance)
		{
			long requestStrartTime = System.currentTimeMillis();
			 request.setAttribute("startTime", requestStrartTime);
			logger.info("URI:" + request.getRequestURI() + "，开始时间 :" + df.format(new Date()));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		
		if (performance)
		{
			long requestEndTime = System.currentTimeMillis();
			long requestStrartTime = (Long) request.getAttribute("startTime");
			long requestTime = requestEndTime - requestStrartTime;
			logger.info("URI:" + request.getRequestURI() + "，结束时间 :" + df.format(new Date()));
			logger.info("URI:" + request.getRequestURI() + "，请求时间：" + String.valueOf(requestTime) + " ms");
		}

	}

}
