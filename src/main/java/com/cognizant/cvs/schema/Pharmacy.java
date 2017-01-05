package com.cognizant.cvs.schema;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

	private String pharmacyId;
	private String state;

	public Pharmacy(String pharmacyId, String state) {
		super();
		this.pharmacyId = pharmacyId;
		this.state = state;
	}

	public Pharmacy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "RphPharmacy [pharmacyId=" + pharmacyId + ", state=" + state + "]";
	}


}
