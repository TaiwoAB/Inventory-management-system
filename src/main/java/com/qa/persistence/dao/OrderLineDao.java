package com.qa.persistence.dao;

import java.util.List;

public interface OrderLineDao<T> {
	 
	 List<T> readAllOrderLine(Long orderId);//make this change in the previous code
     
	 void orderLineCreate(Long orderId, Long itemId, int quantity);
	     
	 void orderLineUpdate(Long id, int quantity);
	     
	 void orderLineDelete(Long id);
	 
	 String getItemIdInnOrderline(Long id);

}
