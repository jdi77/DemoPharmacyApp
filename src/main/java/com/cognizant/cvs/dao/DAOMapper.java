package com.cognizant.cvs.dao;

import java.util.List;

import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.RphPharmacist;
import com.cognizant.cvs.schema.RphPharmacy;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;

public interface DAOMapper {
		
	 public List<WorkItemRequestType> getWorkItems();
	 
	 public void insertWorkItem(WorkItemRequestType workItem);
	 
	 public void insertLineItems(List<LineItemType> lineItems);
	 
	 public List<LineItemType> getLineItems();
	 
	 public void modifyOrderStatus(ModifyWorkItemRequestParam modifyOrder);
	 
	 public List<RphPharmacist> getPharmacist();
	 
	 public List<RphPharmacy> getPharmacys();
	 
	 public int getPharmacysWorkLoad(String pharmacyId);

}
