package com.qa.services;

import java.util.List;

//import com.qa.persistence.dao.ItemDao;
import com.qa.persistence.dao.OrderDao;
import com.qa.persistence.domain.Customer;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Order;

public class OrderServices implements CrudServices<Order>, GetOrderDetails<Order,Item,Customer>,GetCustomerId<Customer>, GetItemId<Item>{
	OrderDao<Order, Item, Customer> orderDao;
	public OrderServices(OrderDao<Order,Item,Customer> orderDao) {
		this.orderDao = orderDao;
	}
	@Override
	public Long getOrderId(Long custId, Double price) {
		return orderDao.getOrderId(custId, price);
	}

	@Override
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	@Override
	public Order create(Order t) {
		return orderDao.create(t);
		
	}

	@Override
	public Order update(Long id, Order t) {
		return orderDao.update(id, t);
		
		
	}

	@Override
	public void delete(Order t) {
		orderDao.delete(t);
		
	}

	@Override
	public Double itemsPrice(Item t) {
		
		return orderDao.itemsPrice(t);
	}

	@Override
	public List<Item> itemsDisplay() {
		return orderDao.itemsDisplay();
		
	}

	@Override
	public Long getCustomerId(Customer t) {
		return orderDao.getCustomerId(t);
	}

	@Override
	public  Long getItemId(Item t) {
	
		return orderDao.getItemId(t);
	}
	@Override
	public List<Order> orderDetailsDisplay(Long id) {
		
		return orderDao.orderDetailsDisplay(id);
	}
	@Override
	public Double getTotalPriceById(Long orderId) {

		return orderDao.getTotalPriceById(orderId);
	}
	





}
