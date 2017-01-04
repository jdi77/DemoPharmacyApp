package com.cognizant.cvs.schema;

import java.util.ArrayList;
import java.util.List;

public class WorkItemRequestTypeList {
	
	private List<WorkItemRequestType> workItemRequestTypes;

	public List<WorkItemRequestType> getWorkItemRequestTypes() {
		if(workItemRequestTypes == null){
			return new ArrayList<WorkItemRequestType>();
		}
		return workItemRequestTypes;
	}

	public void setWorkItemRequestTypes(List<WorkItemRequestType> workItemRequestTypes) {
		this.workItemRequestTypes = workItemRequestTypes;
	}

	@Override
	public String toString() {
		return "WorkItemTypeList [workItemRequestTypes=" + workItemRequestTypes + "]";
	}
	
	

}
