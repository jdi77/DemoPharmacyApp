package com.cognizant.cvs.vo;

import java.util.ArrayList;
import java.util.List;

public class Pharmacist {

	private String id;
	private String state;
	private String certification;
	private String pharmacyId;
	private List<WorkItem> workItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public List<WorkItem> getWorkItems() {
		if(workItems == null){
			return new ArrayList<WorkItem>();
		}
		return workItems;
	}

	public void setWorkItems(List<WorkItem> workItems) {
		this.workItems = workItems;
	}

	@Override
	public String toString() {
		return "Pharmacist [id=" + id + ", state=" + state + ", certification=" + certification + ", pharmacyId="
				+ pharmacyId + ", workItems=" + workItems + "]";
	}

}
