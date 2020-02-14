package com.qa.services;



import java.util.List;

import com.qa.persistence.dao.OrderLineDao;
import com.qa.persistence.domain.OrderLine;



public class OrderLineServices implements GetOrderItemDetails<OrderLine> {
	OrderLineDao<OrderLine> orderLineDao;
	public OrderLineServices(OrderLineDao<OrderLine> orderLineDao) {
		this.orderLineDao = orderLineDao;
	}
	@Override
	public String getItemIdInnOrderline(Long id){

		return orderLineDao.getItemIdInnOrderline(id);
	}
	
	public List<OrderLine> readAllOrderLine(Long orderId) {
		
		return orderLineDao.readAllOrderLine(orderId);
	}
	
	public void orderLineCreate(Long orderId, Long itemId, int quantity) {
	
		orderLineDao.orderLineCreate(orderId, itemId, quantity);
	}

	public void orderLineUpdate(Long id, int quantity ){
	
		orderLineDao.orderLineUpdate(id, quantity);
	}
	
	public void orderLineDelete(Long id) {
		orderLineDao.orderLineDelete(id);
	}
	
	
	
	

}
