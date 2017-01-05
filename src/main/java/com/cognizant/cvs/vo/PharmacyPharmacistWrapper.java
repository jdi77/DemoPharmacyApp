package com.cognizant.cvs.vo;

import static java.util.Collections.emptyList;

import java.util.Collection;

import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;

/**
 * @author Tejaswi Alluri
 */
public class PharmacyPharmacistWrapper {

	private Collection<Pharmacy> pharmacies = emptyList();
	private Collection<Pharmacist> pharmacists = emptyList();

	public PharmacyPharmacistWrapper(Collection<Pharmacy> pharmacies, Collection<Pharmacist> pharmacists) {
		super();
		this.pharmacies = pharmacies;
		this.pharmacists = pharmacists;
	}

	public static PharmacyPharmacistWrapper newPharmacyPharmacistWrapper(Collection<Pharmacy> pharmacies,
			Collection<Pharmacist> pharmacists) {
		return new PharmacyPharmacistWrapper(pharmacies, pharmacists);
	}

	public Collection<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public Collection<Pharmacist> getPharmacists() {
		return pharmacists;
	}

}
