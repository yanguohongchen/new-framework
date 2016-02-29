package com.sea.framework;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sea.exception.BusinessException;
import com.sea.exception.DeniedException;

@ControllerAdvice
public class GlobalExceptionHandler
{
	
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public MsgResult handleException(Exception ex, HttpServletRequest request)
	{
	
		logger.error(ex.getMessage(), ex);
		return new MsgResult(Status.ServerException.toString(),"出错啦！");
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public MsgResult handleBusinessException(Exception ex, HttpServletRequest request)
	{
		logger.warn(ex.getMessage(), ex);
		return new MsgResult(Status.BusinessException.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(DeniedException.class)
	@ResponseBody
	public MsgResult handleDeniedException(Exception ex, HttpServletRequest request)
	{
		logger.warn(ex.getMessage(), ex);
		return new MsgResult(Status.DeniedException.toString(), ex.getMessage());
	}

	
	
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public MsgResult handleBindException(BindException ex, HttpServletRequest request)
	{
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		return new MsgResult("0", fieldError.getField() + ":" + fieldError.getDefaultMessage());
	}

}
