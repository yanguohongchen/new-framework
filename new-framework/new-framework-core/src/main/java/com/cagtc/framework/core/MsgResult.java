package com.cagtc.framework.core;

public class MsgResult {

	public MsgResult(String code, BusinessMsg msg) {
		this(null, code, msg);
	}

	public MsgResult() {
	}

	public MsgResult(Object data) {
		this.data = data;
	}

	public MsgResult(Object data, String code, BusinessMsg businessMsg) {
		this.data = data;
		this.code = code;
		this.businessMsg = businessMsg;
	}

	private String code = Status.OK.toString();

	private BusinessMsg businessMsg = new BusinessMsg();;

	protected Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BusinessMsg getBusinessMsg() {
		return businessMsg;
	}

	public void setBusinessMsg(BusinessMsg businessMsg) {
		this.businessMsg = businessMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
