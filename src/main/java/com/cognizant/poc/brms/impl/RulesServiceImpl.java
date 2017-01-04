package com.cognizant.poc.brms.impl;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.command.CommandFactory;

import com.cognizant.poc.brms.RulesService;

public class RulesServiceImpl implements RulesService {

	@SuppressWarnings("rawtypes")
	@Override
	public void runRules(List<? extends Command> facts) {
		KieServices kieServices = KieServices.Factory.get();
		ReleaseId releaseId = kieServices.newReleaseId("com.cognizant.cvs.poc", "routing-rules", "1.0");
		KieContainer container = kieServices.newKieContainer(releaseId);
		for (AgendaGroup agendaGroup : AgendaGroup.values()) {
			KieSession session = container.newKieSession();
			session.getAgenda().getAgendaGroup(agendaGroup.getValue()).setFocus();
			session.execute(CommandFactory.newBatchExecution(facts));
			session.dispose();
		}
	}

}
