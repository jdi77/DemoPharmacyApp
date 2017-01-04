package com.cognizant.cvs.vo;

import java.util.ArrayList;
import java.util.List;

public class WorkItemList {
	
	private List<WorkItem> workItems;

	public List<WorkItem> getWorkItems() {
		if(workItems == null){
			return new ArrayList<WorkItem>();
		}
		return workItems;
	}

	public void setWorkItems(List<WorkItem> workItems) {
		this.workItems = workItems;
	}

	@Override
	public String toString() {
		return "WorkItemList [workItems=" + workItems + "]";
	}
	

}
