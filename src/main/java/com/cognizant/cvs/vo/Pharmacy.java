package com.cognizant.cvs.vo;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

	private String id;
	private String state;
	private List<Pharmacist> pharmacists;

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

	public List<Pharmacist> getPharmacists() {
		if(pharmacists == null){
			return new ArrayList<Pharmacist>();
		}
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	@Override
	public String toString() {
		return "Pharmacy [id=" + id + ", state=" + state + ", pharmacists=" + pharmacists + "]";
	}

}
