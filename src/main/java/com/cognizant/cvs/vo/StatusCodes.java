package com.cognizant.cvs.vo;

public enum StatusCodes {

	SUCCESS("SUCCESS"), CANCEL("CANCEL"), REVOKE("REVOKE"), PENDING("PENDING"), ORDER("ORDER"),FAILED("FAILED");

	private String status;

	StatusCodes(String status) {
		this.status = status;
	}

	public String status() {
		return status;
	}
}
