package com.cognizant.cvs.controller;

import static com.cognizant.cvs.vo.PharmacyPharmacistWrapper.newPharmacyPharmacistWrapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
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
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.schema.WorkItemRequestTypeList;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;
import com.cognizant.cvs.vo.Status;
import com.cognizant.cvs.vo.StatusCodes;
import com.cognizant.poc.service.RulesService;
import com.cognizant.poc.service.impl.RulesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PharmacyController {

	@Autowired
	public DAOManager dao;

	private static String workItemFileName = "C:/John/PharmacyJson/WorkItemList.json";

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
			// Insert WorkItem
			WorkItemRequestType workItem = new WorkItemRequestType(requestWorkItem.getWorkItemID(),
					requestWorkItem.getWorkItemStatus(), requestWorkItem.getOrder(), requestWorkItem.getNewElement());

			List<Pharmacy> pharmacies = fetchPharmacies();
			List<Pharmacist> pharmacists = dao.getPharmacists();
			rulesService.createFactsAndRunRules(workItem, newPharmacyPharmacistWrapper(pharmacies, pharmacists));
			dao.insertWorkItem(workItem);

			// LineItem Request Object Json doesnt have OrderID , populating it
			for (LineItemType lineItem : requestWorkItem.getOrder().getLineItems()) {
				lineItem.setOrderID(requestWorkItem.getOrder().getOrderID());
			}

			// Insert LineItems
			dao.insertLineItems(requestWorkItem.getOrder().getLineItems());

			return new Status(StatusCodes.SUCCESS.status(), "WorkItem Submitted");
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
			if (modifyWorkItemParam.getStatus().equals(StatusCodes.CANCEL.status())
					|| modifyWorkItemParam.getStatus().equals(StatusCodes.REVOKE.status())) {
				dao.modifyOrderStatus(modifyWorkItemParam);
				dao.deleteOrderFromMapping(modifyWorkItemParam.getOrderId());
			} else {
				return new Status(StatusCodes.FAILED.status(), "Please give a valid status to modify");
			}
			return new Status(StatusCodes.SUCCESS.status(),
					"The Order : " + modifyWorkItemParam.getOrderId() + " has been " + modifyWorkItemParam.getStatus());
		} catch (Exception e) {
			return new Status(StatusCodes.SERVER_ERROR.status(), e.getMessage());
		}
	}

	/**
	 * Place workItem Request. Inserts data as a JSON file in local system
	 * 
	 * @param requestWorkItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitWorkItems", method = RequestMethod.POST, headers = "Accept=application/json")
	public Status submitWorkItems(@RequestBody WorkItemRequestType requestWorkItem) {
		try {
			WorkItemRequestTypeList workItemList = new WorkItemRequestTypeList();

			ObjectMapper mapper = new ObjectMapper();
			Object workItemJsonObject = readFromFile(workItemFileName);

			workItemList = mapper.readValue(workItemJsonObject.toString(), WorkItemRequestTypeList.class);
			if (workItemList.getWorkItemRequestTypes().isEmpty()) {
				List<WorkItemRequestType> newWorkItem = new ArrayList<WorkItemRequestType>();
				newWorkItem.add(requestWorkItem);
				workItemList.setWorkItemRequestTypes(newWorkItem);
				convertJsonToString(workItemList, mapper);
			} else {
				workItemList.getWorkItemRequestTypes().add(requestWorkItem);
				convertJsonToString(workItemList, mapper);
			}

			return new Status(StatusCodes.SUCCESS.status(), "WorkItem Submitted");
		} catch (Exception e) {
			return new Status(StatusCodes.SERVER_ERROR.status(), e.getMessage());
		}
	}

	/**
	 * Get the workLoad of a particular pharamacy
	 * 
	 * @param pharmacyId
	 * @return
	 */
	@RequestMapping(value = "/getPharmacyLoad/{pharmacyId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Status getPhamracyLoad(@PathVariable String pharmacyId) {
		try {
			Integer workLoad = dao.getPharmacysWorkLoad(pharmacyId);
			return new Status(StatusCodes.SUCCESS.status(),
					"The WorkLoad for the Pharmacy  " + pharmacyId + " is :" + workLoad);
		} catch (Exception e) {
			return new Status(StatusCodes.SERVER_ERROR.status(), e.getMessage());
		}
	}

	private void convertJsonToString(WorkItemRequestTypeList workItemList, ObjectMapper mapper)
			throws JsonProcessingException, IOException {
		String jsonStringWorkItem = mapper.writeValueAsString(workItemList);

		writeToFile(jsonStringWorkItem, workItemFileName);
	}

	private void writeToFile(String jsonString, String fileName) throws IOException {
		FileWriter file = new FileWriter(fileName);
		file.write(jsonString);
		file.flush();
		file.close();
	}

	private Object readFromFile(String fileName) throws Exception {
		Object jsonObject = null;
		try {

			JSONParser parser = new JSONParser();
			jsonObject = parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			System.out.println("Caught a exception :" + e.getMessage());
		}
		return jsonObject;
	}

	private List<Pharmacy> fetchPharmacies() {
		List<Pharmacy> pharmacies = dao.getPharmacys();
		for (Pharmacy pharmacy : pharmacies) {
			int workload = dao.getPharmacysWorkLoad(pharmacy.getPharmacyId());
			int availability = 100000 - workload;
			pharmacy.setAvailability(availability);
		}
		return pharmacies;
	}

	/*
	 * @RequestMapping(value = "/pharmacyList", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public PharmacyList
	 * getPharmacyList() throws Exception { PharmacyList listOfPharmacys = new
	 * PharmacyList(); listOfPharmacys = getAllPharmacyList(); return
	 * listOfPharmacys; }
	 * 
	 * @RequestMapping(value = "/pharmacistList", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public List<Pharmacist>
	 * getPharmacistList() throws Exception { List<Pharmacist> pharmacistList =
	 * new ArrayList<Pharmacist>(); PharmacyList pharmacyList =
	 * getAllPharmacyList(); for (Pharmacy pharmacy :
	 * pharmacyList.getPharmacyList()) { for (Pharmacist pharmacist :
	 * pharmacy.getPharmacists()) { pharmacistList.add(pharmacist); } } return
	 * pharmacistList; }
	 * 
	 * public PharmacyList getAllPharmacyList() throws Exception { PharmacyList
	 * pharmacyList = new PharmacyList(); Object pharmacyListJsonObject =
	 * readFromFile(pharmacyListFileName); ObjectMapper mapper = new
	 * ObjectMapper(); pharmacyList =
	 * mapper.readValue(pharmacyListJsonObject.toString(), PharmacyList.class);
	 * System.out.println("Pharmacy Object >> " + pharmacyList.toString());
	 * return pharmacyList; }
	 */

}
