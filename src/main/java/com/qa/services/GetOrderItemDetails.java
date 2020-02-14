package com.qa.services;

import java.util.List;

public interface GetOrderItemDetails<T> {
	String getItemIdInnOrderline(Long id);///get itemnameby orderline change from here, orderservices, dao and others that use it
	 
	 List<T> readAllOrderLine(Long orderId);//make this change in the previous code
     
	 void orderLineCreate(Long orderId, Long itemId, int quantity);
	     
	 void orderLineUpdate(Long id, int quantity);
	     
	 void orderLineDelete(Long id);
}
