package com.cognizant.cvs.schema;

import java.util.ArrayList;
import java.util.List;

public class RPHWorkLoad {

	private String rphID;
	private String certification;
	private Integer rphTotalWorkLoad;
	private List<RPHWorkItem> rphWorkItems;

	public RPHWorkLoad(String rphID, String certification, Integer rphTotalWorkLoad, List<RPHWorkItem> rphWorkItems) {
		super();
		this.rphID = rphID;
		this.certification = certification;
		this.rphTotalWorkLoad = rphTotalWorkLoad;
		this.rphWorkItems = rphWorkItems;
	}

	public RPHWorkLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRphID() {
		return rphID;
	}

	public void setRphID(String rphID) {
		this.rphID = rphID;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public Integer getRphTotalWorkLoad() {
		return rphTotalWorkLoad;
	}

	public void setRphTotalWorkLoad(Integer rphTotalWorkLoad) {
		this.rphTotalWorkLoad = rphTotalWorkLoad;
	}

	public List<RPHWorkItem> getRphWorkItems() {
	
			
		return rphWorkItems;
	}

	public void setRphWorkItems(List<RPHWorkItem> rphWorkItems) {
		this.rphWorkItems = rphWorkItems;
	}

	@Override
	public String toString() {
		return "RPHWorkLoad [rphID=" + rphID + ", certification=" + certification + ", rphTotalWorkLoad="
				+ rphTotalWorkLoad + ", rphWorkItems=" + rphWorkItems + "]";
	}

}
