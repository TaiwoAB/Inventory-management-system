package com.qa.controller;
/**
 * A mini-controller interface  which has methods to implement the CRUD principles for orderline
 * @author tolaa
 *
 */
public interface GetOrderItemDetailsController {
	  
    void readAllOrderLine(Long orderId);
     
    void orderLineCreate(Long orderId, Long itemId, int quantity);
     
    void orderLineUpdate(Long id, int qauntity);
     
    void orderLineDelete(Long id);
    String getItemIdInnOrderline(Long id);
   
}
