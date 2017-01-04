package com.cognizant.cvs.vo;

import java.util.ArrayList;
import java.util.List;

public class WorkItem {

	private String id;
	private String clientId;
	private String status;
	private List<LineItem> lineItems;
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LineItem> getLineItems() {
		if(lineItems == null){
			return new ArrayList<LineItem>();
		}
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "WorkItem [id=" + id + ", clientId=" + clientId + ", status=" + status + ", lineItems=" + lineItems
				+ ", state=" + state + "]";
	}
}
