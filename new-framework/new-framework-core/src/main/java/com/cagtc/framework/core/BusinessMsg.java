package com.cagtc.framework.core;

public class BusinessMsg {

	public BusinessMsg() {

	}

	public BusinessMsg(int businessCode, String businessNote) {
		this.businessCode = businessCode;
		this.businessNote = businessNote;
	}

	private int businessCode = 0;

	private String businessNote = "Success";

	public int getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(int businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessNote() {
		return businessNote;
	}

	public void setBusinessNote(String businessNote) {
		this.businessNote = businessNote;
	}

}
