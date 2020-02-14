package com.qa.controller;

import java.util.List;
/**
 * Interface which contains methods to obtain order details of the customer
 * @author tolaa
 *
 * @param <T>
 */
public interface GetOrderIdController<T> {
	List<T> orderDetailsDisplay(Long id);
	Long getOrderId(Long custId, Double price);
	 Double getTotalPriceById(Long orderId);
}
