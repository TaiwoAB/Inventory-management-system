package com.qa.controller;

import java.util.List;

public interface GetOrderIdController<T> {
	List<T> orderDetailsDisplay(Long id);
	Long getOrderId(Long custId, Double price);
	 Double getTotalPriceById(Long orderId);
}
