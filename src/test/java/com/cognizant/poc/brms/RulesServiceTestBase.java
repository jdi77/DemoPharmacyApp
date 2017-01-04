package com.cognizant.poc.brms;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.cvs.poc.routing_rules.LineItem;
import com.cognizant.cvs.poc.routing_rules.Rph;
import com.cognizant.cvs.poc.routing_rules.Store;
import com.cognizant.cvs.poc.routing_rules.WorkItem;

import junit.framework.TestCase;

public class RulesServiceTestBase extends TestCase {

	protected WorkItem newWorkItem(String id, String clientId, String status, List<LineItem> lineItems, String state) {
		WorkItem workItem = new WorkItem(id, clientId, status, lineItems, state, false);
		return workItem;
	}

	protected LineItem newLineItem(String id, String name, String state, String orderId) {
		LineItem lineItem = new LineItem(id, name, state, orderId);
		return lineItem;
	}

	protected Rph newRph(String id, String state, String certification, String pharmacyId) {
		Rph rph = new Rph(id, state, certification, pharmacyId, new ArrayList<WorkItem>(), false);
		return rph;
	}

	protected Store newStore(String id, String state, List<Rph> pharmacists, boolean shortlisted, int availability) {
		Store store = new Store(id, state, pharmacists, false, availability);
		return store;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> asList(T... objects) {
		List<T> list = new ArrayList<T>();
		for (T object : objects)
			list.add(object);
		return list;
	}

}
