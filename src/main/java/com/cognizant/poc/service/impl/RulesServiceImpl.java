package com.cognizant.poc.service.impl;

import static org.kie.internal.command.CommandFactory.newFireAllRules;
import static org.kie.internal.command.CommandFactory.newInsertElements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.cognizant.cvs.poc.routing_rules.Store;
import com.cognizant.cvs.poc.routing_rules.WorkItem;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.PharmacyPharmacistWrapper;
import com.cognizant.poc.brms.RulesRunner;
import com.cognizant.poc.brms.impl.RulesRunnerImpl;
import com.cognizant.poc.converter.PharmacyToFactConverter;
import com.cognizant.poc.converter.WorkItemRequestToFactConverter;
import com.cognizant.poc.service.RulesService;

/**
 * @author Tejaswi Alluri
 */
public class RulesServiceImpl implements RulesService {

	private RulesRunner rulesRunner = new RulesRunnerImpl();
	private Converter<WorkItemRequestType, WorkItem> workItemConverter = new WorkItemRequestToFactConverter();
	private Converter<PharmacyPharmacistWrapper, Collection<Store>> storeConverter = new PharmacyToFactConverter();

	@Override
	public void createFactsAndRunRules(WorkItemRequestType requestWorkItem, PharmacyPharmacistWrapper wrapper) {
		WorkItem workItem = workItemConverter.convert(requestWorkItem);
		Collection<Store> stores = storeConverter.convert(wrapper);
		rulesRunner.runRules(asList(newInsertElements(stores), newInsertElements(asList(workItem)), newFireAllRules()));
	}

	private <T> List<T> asList(@SuppressWarnings("unchecked") T... objects) {
		List<T> list = new ArrayList<T>();
		for (T object : objects)
			list.add(object);
		return list;
	}

}
