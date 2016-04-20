package com.cagtc.framework.core;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagtc.framework.BaseServiceException;
import com.cagtc.framework.exception.DeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public MsgResult handleException(Exception ex, HttpServletRequest request) {
		if (ex.getClass().getSimpleName().equals("ClientAbortException")) {
			//无视客户端强制断开异常
		} else {
			logger.error(ex.getMessage(), ex);
		}
		return new MsgResult(Status.ServerException.toString(), new BusinessMsg(0, ex.getMessage()));
	}

	@ExceptionHandler(BaseServiceException.class)
	@ResponseBody
	public MsgResult handleBusinessException(BaseServiceException ex, HttpServletRequest request) {
		logger.info(ex.getMessage());
		return new MsgResult(Status.BusinessException.toString(), new BusinessMsg(ex.getCode(), ex.getMessage()));
	}

	@ExceptionHandler(DeniedException.class)
	@ResponseBody
	public MsgResult handleDeniedException(Exception ex, HttpServletRequest request) {
		logger.debug(ex.getMessage(), ex);
		return new MsgResult(Status.DeniedException.toString(), new BusinessMsg(0, ex.getMessage()));
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public MsgResult handleBindException(BindException ex, HttpServletRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		return new MsgResult("0", new BusinessMsg(0, fieldError.getField() + ":" + fieldError.getDefaultMessage()));
	}

}
