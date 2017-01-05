package com.cognizant.poc.brms.impl;

import static org.kie.internal.command.CommandFactory.newFireAllRules;
import static org.kie.internal.command.CommandFactory.newInsertElements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.cognizant.cvs.poc.routing_rules.WorkItem;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.poc.brms.RulesRunner;
import com.cognizant.poc.brms.RulesService;
import com.cognizant.poc.converter.WorkItemRequestToFactConverter;

public class RulesServiceImpl implements RulesService {

	private RulesRunner rulesRunner = new RulesRunnerImpl();
	private Converter<WorkItemRequestType, WorkItem> workItemConverter = new WorkItemRequestToFactConverter();

	@Override
	public void createFactsAndRunRules(WorkItemRequestType requestWorkItem) {
		WorkItem workItem = workItemConverter.convert(requestWorkItem);
		rulesRunner.runRules(asList(newInsertElements(asList(workItem)), newFireAllRules()));
	}

	private <T> List<T> asList(@SuppressWarnings("unchecked") T... objects) {
		List<T> list = new ArrayList<T>();
		for (T object : objects)
			list.add(object);
		return list;
	}

}
