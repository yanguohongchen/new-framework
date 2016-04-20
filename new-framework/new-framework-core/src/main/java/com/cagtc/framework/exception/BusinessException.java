package com.cagtc.framework.exception;

import com.cagtc.framework.BaseServiceException;
import com.cagtc.framework.EnumVal;

/**
 * 业务异常
 * 
 * @author sea
 *
 */
public class BusinessException extends BaseServiceException implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	public BusinessException() {

	}

	public BusinessException(int bussinesCode, String msg) {
		super(bussinesCode,msg);
	}

	public BusinessException(EnumVal errorCode) {
		this(errorCode.getCode(), errorCode.getMsg());
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}