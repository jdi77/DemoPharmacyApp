package com.cognizant.cvs.dao;

import java.util.List;

import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;

public interface DAOMapper {
		
	 public List<WorkItemRequestType> getWorkItems();
	 
	 public void insertWorkItem(WorkItemRequestType workItem);
	 
	 public void insertLineItems(List<LineItemType> lineItems);
	 
	 public List<LineItemType> getLineItems();
	 
	 public void modifyOrderStatus(ModifyWorkItemRequestParam modifyOrder);
	 
	 public List<Pharmacist> getPharmacist();
	 
	 public List<Pharmacy> getPharmacys();
	 
	 public int getPharmacysWorkLoad(String pharmacyId);
	 
	 public void deleteOrderFromMapping(String orderId);

}
