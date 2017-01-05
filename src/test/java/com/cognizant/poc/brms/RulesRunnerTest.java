package com.cognizant.poc.brms;

import static org.kie.internal.command.CommandFactory.newFireAllRules;
import static org.kie.internal.command.CommandFactory.newInsertElements;

import com.cognizant.cvs.poc.routing_rules.LineItem;
import com.cognizant.cvs.poc.routing_rules.Rph;
import com.cognizant.cvs.poc.routing_rules.Store;
import com.cognizant.cvs.poc.routing_rules.WorkItem;
import com.cognizant.poc.brms.impl.RulesRunnerImpl;
import com.cognizant.poc.service.impl.RulesServiceImpl;

/**
 * @author Tejaswi Alluri
 */
public class RulesRunnerTest extends RulesRunnerTestBase {

	private RulesRunner rulesRunner = new RulesRunnerImpl();

	public void testWorkItemNeedsCertification() {
		LineItem lineItem = newLineItem("1", "xyz", "NH", "1");
		WorkItem workItem = newWorkItem("1", "abc", null, asList(lineItem), "NH");

		rulesRunner.runRules(asList(newInsertElements(asList(workItem)), newFireAllRules()));
		assertEquals("QUALIFIED", workItem.getStatus());
		assertTrue(workItem.getCertificationNeeded());
	}

	public void testQualifiedStore() {
		LineItem lineItem = newLineItem("1", "xyz", "NH", "1");
		WorkItem workItem = newWorkItem("1", "abc", null, asList(lineItem), "NH");
		Rph rph1 = newRph("1", "NH", "xyz_c", "1");
		Rph rph2 = newRph("2", "NH", "abc", "1");
		Rph rph3 = newRph("3", "CO", "xyz_c", "2");
		Rph rph4 = newRph("4", "CO", "abc", "2");
		Rph rph5 = newRph("5", "NH", "ghs", "3");
		Rph rph6 = newRph("6", "NH", "abc", "3");
		Rph rph7 = newRph("7", "NH", "pog", "4");
		Rph rph8 = newRph("8", "NH", "xyz_c", "4");
		Store store1 = newStore("1", "NH", asList(rph1, rph2), false, 10);
		Store store2 = newStore("2", "CO", asList(rph3, rph4), false, 10);
		Store store3 = newStore("3", "CO", asList(rph5, rph6), false, 10);
		Store store4 = newStore("4", "NH", asList(rph7, rph8), false, 20);

		rulesRunner.runRules(asList(newInsertElements(asList(workItem, store1, store2, store3, store4)), newFireAllRules()));
		assertTrue(workItem.getCertificationNeeded());
		assertTrue(store4.isShortlisted());
		assertTrue(store1.isShortlisted());
		assertFalse(store2.isShortlisted());
		assertFalse(store3.isShortlisted());
		assertEquals(1, store4.getPharmacists().get(1).getWorkItems().size());
		assertEquals("PENDING", store4.getPharmacists().get(1).getWorkItems().get(0).getStatus());
	}

}
