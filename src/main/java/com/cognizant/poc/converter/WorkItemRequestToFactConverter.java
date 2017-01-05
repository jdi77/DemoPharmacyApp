package com.cognizant.poc.converter;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.cognizant.cvs.poc.routing_rules.LineItem;
import com.cognizant.cvs.poc.routing_rules.WorkItem;
import com.cognizant.cvs.schema.LineItemType;
import com.cognizant.cvs.schema.OrderType;
import com.cognizant.cvs.schema.PrescriberDetailsType;
import com.cognizant.cvs.schema.WorkItemRequestType;

public class WorkItemRequestToFactConverter implements Converter<WorkItemRequestType, WorkItem> {

	@Override
	public WorkItem convert(WorkItemRequestType requestWorkItem) {
		OrderType order = requestWorkItem.getOrder();
		PrescriberDetailsType prescriber = order.getPrescriberDetails();
		List<LineItemType> requestLineItems = order.getLineItems();
		List<LineItem> lineItems = new ArrayList<LineItem>();
		for (int i = 0; i < requestLineItems.size(); i++) {
			lineItems.add(convert(i, requestLineItems.get(i), prescriber, order));
		}
		WorkItem workItem = new WorkItem(order.getOrderID(), order.getClientID(), null, lineItems,
				prescriber.getState(), true);
		return workItem;
	}

	private LineItem convert(int id, LineItemType requestLineItem, PrescriberDetailsType prescriber, OrderType order) {
		LineItem lineItem = new LineItem(valueOf(id), requestLineItem.getDrugName(), prescriber.getPrescriberId(),
				order.getOrderID());
		return lineItem;
	}

}
