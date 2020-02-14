package com.qa.controller;

public interface GetOrderItemDetailsController {
	  
    void readAllOrderLine(Long orderId);//make this change in the previous code
     
    void orderLineCreate(Long orderId, Long itemId, int quantity);
     
    void orderLineUpdate(Long id, int qauntity);
     
    void orderLineDelete(Long id);
    String getItemIdInnOrderline(Long id);
   
}
