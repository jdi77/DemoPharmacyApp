package com.cognizant.cvs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;

public class DAOManager {

	static SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();
	static DAOMapper daoMapper = sqlSession.getMapper(DAOMapper.class);

	public List<com.cognizant.cvs.schema.WorkItemRequestType> getWorkItems() {
		return daoMapper.getWorkItems();
	}

	public void insertWorkItem(WorkItemRequestType workItem) {
		daoMapper.insertWorkItem(workItem);
		sqlSession.commit();
	}

	public void insertLineItems(List<LineItemType> lineItems) {
		daoMapper.insertLineItems(lineItems);
		sqlSession.commit();
	}

	public List<LineItemType> getLineItems() {
		return daoMapper.getLineItems();
	}

	public void modifyOrderStatus(ModifyWorkItemRequestParam modifyOrder) {
		daoMapper.modifyOrderStatus(modifyOrder);
		sqlSession.commit();
	}

	public List<Pharmacist> getPharmacists() {
		return daoMapper.getPharmacist();
	}

	public List<Pharmacy> getPharmacys() {
		return daoMapper.getPharmacys();
	}

	public int getPharmacysWorkLoad(String pharmacyId) {
		return daoMapper.getPharmacysWorkLoad(pharmacyId);
	}
	
	public void deleteOrderFromMapping(String orderId) {
		daoMapper.deleteOrderFromMapping(orderId);
		sqlSession.commit();
	}
}
