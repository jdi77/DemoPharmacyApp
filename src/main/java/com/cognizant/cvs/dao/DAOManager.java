package com.cognizant.cvs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.Pharmacist;
import com.cognizant.cvs.schema.Pharmacy;
import com.cognizant.cvs.schema.PharmacyWorkLoadResponse;
import com.cognizant.cvs.schema.RPHWorkItemMapping;
import com.cognizant.cvs.schema.WorkItemRequestType;
import com.cognizant.cvs.vo.ModifyWorkItemRequestParam;

public class DAOManager {

	static SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();
	static DAOMapper daoMapper = sqlSession.getMapper(DAOMapper.class);

	public List<com.cognizant.cvs.schema.WorkItemRequestType> getWorkItems(String orderId) {
		return daoMapper.getWorkItems(orderId);
	}

	public void insertWorkItem(WorkItemRequestType workItem) {
		daoMapper.insertWorkItem(workItem);
		sqlSession.commit();
	}

	public void insertLineItems(List<LineItemType> lineItems) {
		daoMapper.insertLineItems(lineItems);
		sqlSession.commit();
	}

	public List<LineItemType> getLineItems(String orderId) {
		return daoMapper.getLineItems(orderId);
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

	public PharmacyWorkLoadResponse getPharmacysWorkLoad(String pharmacyId) {
		return daoMapper.getPharmacysWorkLoad(pharmacyId);
	}

	public void deleteOrderFromMapping(String orderId) {
		daoMapper.deleteOrderFromMapping(orderId);
		sqlSession.commit();
	}

	public void insertRphMapping(List<RPHWorkItemMapping> rphWorkItemMapping) {
		daoMapper.insertRphMapping(rphWorkItemMapping);
		sqlSession.commit();
	}

}
