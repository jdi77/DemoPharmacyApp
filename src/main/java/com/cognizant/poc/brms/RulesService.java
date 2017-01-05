package com.cognizant.poc.brms;

import com.cognizant.cvs.schema.WorkItemRequestType;

public interface RulesService {

	void createFactsAndRunRules(WorkItemRequestType requestWorkItem);

}
