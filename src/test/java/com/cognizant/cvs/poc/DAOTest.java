package com.cognizant.cvs.poc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.cognizant.cvs.dao.DAOManager;
import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.OrderType;
import com.cognizant.cvs.schema.PrescriberDetailsType;
import com.cognizant.cvs.schema.RPHWorkItemMapping;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;
import com.cognizant.cvs.vo.StatusCodes;


public class DAOTest {

	@Ignore
	@Test
	public void testDatabaseQueries() {
		DAOManager dao = new DAOManager();
		
		PrescriberDetailsType ps = new PrescriberDetailsType("XXX", "prescriberId1", "GA");
		LineItemType lineItem1 = new LineItemType("paracetoml", "10", "rphID1","o1");
		LineItemType lineItem2 = new LineItemType("crocin", "10", "rphID1","o1");

		List<LineItemType> listOfLineItems = new ArrayList<LineItemType>();
		listOfLineItems.add(lineItem1);
		listOfLineItems.add(lineItem2);

		OrderType order1 = new OrderType("PENDING", "o1", "cl1", ps, listOfLineItems);

		WorkItemRequestType workItem = new WorkItemRequestType("w1", "PENDING", order1, "newElement");

		dao.insertWorkItem(workItem);

		List<com.cognizant.cvs.schema.WorkItemRequestType> category = dao.getWorkItems();
		System.out.println("WorkItems o/p : " + category.toString());

		System.out.println(" Get lineItems >> " + dao.getLineItems());
		dao.insertLineItems(listOfLineItems);

		System.out.println(" Get lineItems >> " + dao.getLineItems());

	}
	
	@Ignore
	@Test
	public void modifyOrderStatus(){
		DAOManager dao = new DAOManager();
		ModifyWorkItemRequestParam modifyWorkItem = new ModifyWorkItemRequestParam("orderID1","rphId1",StatusCodes.CANCEL.status());
		dao.modifyOrderStatus(modifyWorkItem);
		List<com.cognizant.cvs.schema.WorkItemRequestType> category = dao.getWorkItems();
		System.out.println("WorkItems o/p : " + category.toString());
	}
	
	@Ignore
	@Test
	public void getPharmacist() {
		DAOManager dao = new DAOManager();
		System.out.println("Pharmacist List : " + dao.getPharmacist());

		System.out.println("Pharmacsy List : " + dao.getPharmacys());

		System.out.println("Pharmacy Work Load " + dao.getPharmacysWorkLoad("cvs1"));
	}
	
	@Test
	public void mappingTableQuery() {
		DAOManager dao = new DAOManager();
		List<RPHWorkItemMapping> rphMappingList = new ArrayList<RPHWorkItemMapping>();
		RPHWorkItemMapping mapping1 = new RPHWorkItemMapping("person2", "orderID2");
		rphMappingList.add(mapping1);
		dao.insertRphMapping(rphMappingList);
	}
}
