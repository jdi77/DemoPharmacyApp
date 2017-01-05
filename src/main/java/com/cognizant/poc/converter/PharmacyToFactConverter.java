package com.cognizant.poc.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.cognizant.cvs.poc.routing_rules.Rph;
import com.cognizant.cvs.poc.routing_rules.Store;
import com.cognizant.cvs.poc.routing_rules.WorkItem;
import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;
import com.cognizant.cvs.vo.PharmacyPharmacistWrapper;

/**
 * @author Tejaswi Alluri
 */
public class PharmacyToFactConverter implements Converter<PharmacyPharmacistWrapper, Collection<Store>> {

	@Override
	public Collection<Store> convert(PharmacyPharmacistWrapper wrapper) {
		Collection<Pharmacy> pharmacies = wrapper.getPharmacies();
		Collection<Pharmacist> pharmacists = wrapper.getPharmacists();
		Collection<Store> stores = new ArrayList<Store>();
		for (Pharmacy pharmacy : pharmacies) {
			stores.add(createStore(pharmacy, pharmacists));
		}
		return stores;
	}

	private Store createStore(Pharmacy pharmacy, Collection<Pharmacist> pharmacists) {
		Store store = new Store(pharmacy.getPharmacyId(), pharmacy.getState(), createRphList(pharmacy, pharmacists),
				false, pharmacy.getAvailability());
		return store;
	}

	private List<Rph> createRphList(Pharmacy pharmacy, Collection<Pharmacist> pharmacists) {
		List<Rph> rphList = new ArrayList<Rph>();
		for (Pharmacist pharmacist : pharmacists) {
			if (pharmacy.getPharmacyId().equals(pharmacist.getPharmacyId())) {
				rphList.add(createRph(pharmacist));
			}
		}
		return rphList;
	}

	private Rph createRph(Pharmacist pharmacist) {
		Rph rph = new Rph(pharmacist.getPharmacistId(), pharmacist.getState(), pharmacist.getCertification(),
				pharmacist.getPharmacyId(), new ArrayList<WorkItem>(), false);
		return rph;
	}

}
