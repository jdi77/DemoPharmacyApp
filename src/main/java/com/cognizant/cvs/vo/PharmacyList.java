package com.cognizant.cvs.vo;

import java.util.ArrayList;
import java.util.List;

public class PharmacyList {
	
	private List<Pharmacy> pharmacyList;

	public List<Pharmacy> getPharmacyList() {
		if(pharmacyList == null){
			return new ArrayList<Pharmacy>();
		}
		return pharmacyList;
	}

	public void setPharmacyList(List<Pharmacy> pharmacyList) {
		this.pharmacyList = pharmacyList;
	}

	@Override
	public String toString() {
		return "PharmacyList [pharmacyList=" + pharmacyList + "]";
	}

}
