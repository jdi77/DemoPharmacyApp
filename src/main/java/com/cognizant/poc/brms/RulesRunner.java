package com.cognizant.poc.brms;

import java.util.List;

import org.kie.api.command.Command;

/**
 * @author Tejaswi Alluri
 */
public interface RulesRunner {

	@SuppressWarnings("rawtypes")
	void runRules(List<? extends Command> facts);

}
