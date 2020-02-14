package com.qa.services;

import java.util.List;

import com.qa.persistence.domain.Item;

public interface GetOrderDetails<T, U, V> {
	Long getOrderId(Long custId, Double price);
	Double itemsPrice(U u);
	public List<Item> itemsDisplay();
    Long getCustomerId(V v);
    Long getItemId(U u);
    List<T> orderDetailsDisplay(Long id);
    Double getTotalPriceById(Long orderId);

}
