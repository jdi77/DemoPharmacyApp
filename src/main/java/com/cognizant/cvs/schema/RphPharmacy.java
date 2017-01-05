package com.cognizant.cvs.schema;

public class RphPharmacy {

	private String pharmacyId;
	private String state;

	public RphPharmacy(String pharmacyId, String state) {
		super();
		this.pharmacyId = pharmacyId;
		this.state = state;
	}

	public RphPharmacy() {
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
