package com.qa.persistence.dao;

import java.util.List;


public interface OrderDao<T, U, V>{
    List<T> readAll();
    
   T create(T t);
     
    T update(long id, T t);
     
    void delete(T t);
    
    List<U>itemsDisplay();
    List<T> orderDetailsDisplay(Long id);
    Double itemsPrice(U u);
    Long getCustomerId(V v);
    Long getOrderId(Long custId, Double price);
    Long getItemId(U u);
    Double getTotalPriceById(Long orderId);
  
}
