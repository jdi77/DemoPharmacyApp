package com.cognizant.cvs.vo;

public class Status {

	private String statusCode;
	private String statusMessage;
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "Status [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}
	
	
}
