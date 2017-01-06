package com.cognizant.cvs.controller;

import static com.cognizant.cvs.vo.PharmacyPharmacistWrapper.newPharmacyPharmacistWrapper;
import static com.cognizant.cvs.vo.StatusCodes.CANCEL;
import static com.cognizant.cvs.vo.StatusCodes.REVOKE;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cvs.dao.DAOManager;
import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;
import com.cognizant.cvs.schema.PharmacyWorkLoadResponse;
import com.cognizant.cvs.schema.RPHWorkItemMapping;
import com.cognizant.cvs.schema.RPHWorkLoad;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;
import com.cognizant.cvs.vo.Status;
import com.cognizant.cvs.vo.StatusCodes;
import com.cognizant.poc.service.RulesService;
import com.cognizant.poc.service.impl.RulesServiceImpl;

@RestController
public class PharmacyController {

	@Autowired
	public DAOManager dao;

	private RulesService rulesService = new RulesServiceImpl();

	/**
	 * Place workItem Request. Inserts data in MySQL Database
	 * 
	 * @param requestWorkItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public Status placeOrder(@RequestBody WorkItemRequestType requestWorkItem) {
		try {
			WorkItemRequestType workItem = new WorkItemRequestType(requestWorkItem.getWorkItemID(),
					requestWorkItem.getWorkItemStatus(), requestWorkItem.getOrder(), requestWorkItem.getNewElement());
			RPHWorkItemMapping mapping = callRulesService(workItem);
			dao.insertWorkItem(workItem);
			for (LineItemType lineItem : requestWorkItem.getOrder().getLineItems())
				lineItem.setOrderID(requestWorkItem.getOrder().getOrderID());
			dao.insertLineItems(requestWorkItem.getOrder().getLineItems());
			dao.insertRphMapping(Arrays.asList(mapping));
			dao.modifyOrderStatus(new ModifyWorkItemRequestParam(mapping.getOrderId(), mapping.getPharmacistId(),
					mapping.getStatus()));
			return new Status(StatusCodes.SUCCESS.status(),
					"WorkItem assigned to the RPH : " + mapping.getPharmacistId());
		} catch (Exception e) {
			return new Status(StatusCodes.SERVER_ERROR.status(), e.getMessage());
		}

	}

	/**
	 * Modify the existing workItem status either to CANCEL or REVOKE
	 * 
	 * @param modifyWorkItemParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyWorkItem", method = RequestMethod.POST, headers = "Accept=application/json")
	public Status modifyWorkItem(@RequestBody ModifyWorkItemRequestParam modifyWorkItemParam) {
		try {
			String status = modifyWorkItemParam.getStatus();
			String orderId = modifyWorkItemParam.getOrderId();
			RPHWorkItemMapping mapping = new RPHWorkItemMapping();
			if (status.equals(CANCEL.status()) || status.equals(REVOKE.status())) {
				dao.modifyOrderStatus(modifyWorkItemParam);
				dao.deleteOrderFromMapping(modifyWorkItemParam.getOrderId());
				if (status.equals(REVOKE.status())) {
					List<WorkItemRequestType> workItems = dao.getWorkItems(orderId);
					List<LineItemType> lineItems = dao.getLineItems(orderId);
					WorkItemRequestType workItem = workItems.get(0);
					workItem.getOrder().getLineItems().addAll(lineItems);
					mapping = callRulesService(workItem);
					dao.insertRphMapping(Arrays.asList(mapping));
				}
				if (status.equals(CANCEL.status())) {
					return new Status(StatusCodes.SUCCESS.status(),
							"The Order : " + modifyWorkItemParam.getOrderId() + " has been CANCELLED ");
				} else {
					return new Status(StatusCodes.SUCCESS.status(), "The Order : " + modifyWorkItemParam.getOrderId()
							+ " has been REVOVKED and assigned to the RPH : " + mapping.getPharmacistId());
				}

			} else {
				return new Status(StatusCodes.FAILED.status(),
						"Please give a valid status to modify either CANCEL or REVOKE");
			}
		} catch (Exception e) {
			return new Status(StatusCodes.SERVER_ERROR.status(), e.getMessage());
		}
	}
	
	
	/**
	 *  Get the workLoad of a particular pharamacy
	 *  
	 * @param pharmacyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPharmacyLoad/{pharmacyId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public PharmacyWorkLoadResponse getPharmacyLoad(@PathVariable String pharmacyId) throws Exception {
		
		PharmacyWorkLoadResponse workLoad = dao.getPharmacysWorkLoad(pharmacyId);
		if (workLoad != null) {
			int storeWorkLoad = getStoreWorkLoad(workLoad);
			workLoad.setStoreTotalWorkLoad(storeWorkLoad);
			return workLoad;
		}
		return new PharmacyWorkLoadResponse();

	}

	private int getStoreWorkLoad(PharmacyWorkLoadResponse workLoad) {
		int storeWorkLoad=0;
		for (RPHWorkLoad rphload : workLoad.getIndividualRPHWorkLoadList()) {
			storeWorkLoad += rphload.getRphWorkItems().size();
			rphload.setRphTotalWorkLoad(rphload.getRphWorkItems().size());
		}
		return storeWorkLoad;
	}

	private RPHWorkItemMapping callRulesService(WorkItemRequestType workItem) {
		List<Pharmacy> pharmacies = fetchPharmacies();
		List<Pharmacist> pharmacists = dao.getPharmacists();
		RPHWorkItemMapping mapping = rulesService.createFactsAndRunRules(workItem,
				newPharmacyPharmacistWrapper(pharmacies, pharmacists));
		return mapping;
	}

	
	private List<Pharmacy> fetchPharmacies() {
		List<Pharmacy> pharmacies = dao.getPharmacys();
		for (Pharmacy pharmacy : pharmacies) {			
			int workload = dao.getPharmacysWorkLoadCount(pharmacy.getPharmacyId());
			int availability = 100000 - workload;
			pharmacy.setAvailability(availability);
		}
		return pharmacies;
	}

	

}
