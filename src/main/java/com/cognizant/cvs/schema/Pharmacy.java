package com.cognizant.cvs.schema;

public class Pharmacy {

	private String pharmacyId;
	private String state;
	private int availability;

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

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Pharmacy [pharmacyId=" + pharmacyId + ", state=" + state + ", availability=" + availability + "]";
	}

}
