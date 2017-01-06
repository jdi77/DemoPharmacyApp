package com.cognizant.cvs.schema;

import java.util.ArrayList;
import java.util.List;

public class PharmacyWorkLoadResponse {

	private String storeId;
	private Integer storeTotalWorkLoad;
	private List<RPHWorkLoad> individualRPHWorkLoadList;

	public PharmacyWorkLoadResponse(String storeId, Integer storeTotalWorkLoad,
			List<RPHWorkLoad> individualRPHWorkLoadList) {
		super();
		this.storeId = storeId;
		this.storeTotalWorkLoad = storeTotalWorkLoad;
		this.individualRPHWorkLoadList = individualRPHWorkLoadList;
	}

	public PharmacyWorkLoadResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreTotalWorkLoad() {
		return storeTotalWorkLoad;
	}

	public void setStoreTotalWorkLoad(Integer storeTotalWorkLoad) {
		this.storeTotalWorkLoad = storeTotalWorkLoad;
	}

	public List<RPHWorkLoad> getIndividualRPHWorkLoadList() {
		
			
		return individualRPHWorkLoadList;
	}

	public void setIndividualRPHWorkLoadList(List<RPHWorkLoad> individualRPHWorkLoadList) {
		this.individualRPHWorkLoadList = individualRPHWorkLoadList;
	}

	@Override
	public String toString() {
		return "PharmacyWorkLoadResponse [storeId=" + storeId + ", storeTotalWorkLoad=" + storeTotalWorkLoad
				+ ", individualRPHWorkLoadList=" + individualRPHWorkLoadList + "]";
	}

}
