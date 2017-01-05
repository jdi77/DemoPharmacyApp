package com.cognizant.cvs.schema;


public class RphPharmacist {
	
	private String pharmacistId;
	private String state;
	private String certification;
	private String pharmacyId;
	public RphPharmacist(String pharmacistId, String state, String certification, String pharmacyId) {
		super();
		this.pharmacistId = pharmacistId;
		this.state = state;
		this.certification = certification;
		this.pharmacyId = pharmacyId;
	}
	public RphPharmacist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPharmacistId() {
		return pharmacistId;
	}
	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
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
	@Override
	public String toString() {
		return "RphPharmacist [pharmacistId=" + pharmacistId + ", state=" + state + ", certification=" + certification
				+ ", pharmacyId=" + pharmacyId + "]";
	}
	
	

}
