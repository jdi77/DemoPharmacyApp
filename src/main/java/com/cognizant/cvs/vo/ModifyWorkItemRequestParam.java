package com.cognizant.cvs.vo;

public class ModifyWorkItemRequestParam {

	private String orderId;
	private String rphId;
	private String status;

	public ModifyWorkItemRequestParam(String orderId, String rphId, String status) {
		super();
		this.orderId = orderId;
		this.rphId = rphId;
		this.status = status;
	}

	public ModifyWorkItemRequestParam() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRphId() {
		return rphId;
	}

	public void setRphId(String rphId) {
		this.rphId = rphId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ModifyWorkItemRequestParam [orderId=" + orderId + ", rphId=" + rphId + ", status=" + status + "]";
	}

}
