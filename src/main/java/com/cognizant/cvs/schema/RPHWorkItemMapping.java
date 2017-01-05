package com.cognizant.cvs.schema;

public class RPHWorkItemMapping {

	private String pharmacistId;
	private String orderId;

	public RPHWorkItemMapping(String pharmacistId, String orderId) {
		super();
		this.pharmacistId = pharmacistId;
		this.orderId = orderId;
	}

	public RPHWorkItemMapping() {
		super();		
	}

	public String getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "RPHWorkItemMapping [pharmacistId=" + pharmacistId + ", orderId=" + orderId + "]";
	}

}
