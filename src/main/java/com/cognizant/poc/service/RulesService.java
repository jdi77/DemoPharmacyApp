package com.cognizant.poc.service;

import com.cognizant.cvs.schema.RPHWorkItemMapping;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.PharmacyPharmacistWrapper;

/**
 * @author Tejaswi Alluri
 */
public interface RulesService {

	RPHWorkItemMapping createFactsAndRunRules(WorkItemRequestType requestWorkItem, PharmacyPharmacistWrapper wrapper);

}
