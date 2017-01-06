package com.cognizant.cvs.schema;

import com.cognizant.cvs.vo.StatusCodes;

public class RPHWorkItem {
	private String orderId;
	private String status;
	private String client;
	private String prescriberId;
	private String prescriberstate;

	public RPHWorkItem(String orderId, String status, String client, String prescriberId, String prescriberstate) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.client = client;
		this.prescriberId = prescriberId;
		this.prescriberstate = prescriberstate;
	}

	public RPHWorkItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPrescriberId() {
		return prescriberId;
	}

	public void setPrescriberId(String prescriberId) {
		this.prescriberId = prescriberId;
	}

	public String getPrescriberstate() {
		return prescriberstate;
	}

	public void setPrescriberstate(String prescriberstate) {
		this.prescriberstate = prescriberstate;
	}

	@Override
	public String toString() {
		return "RPHWorkItem [orderId=" + orderId + ", status=" + status + ", client=" + client + ", prescriberId="
				+ prescriberId + ", prescriberstate=" + prescriberstate + "]";
	}

}
