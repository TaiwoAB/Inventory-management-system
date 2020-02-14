package com.qa.persistence.dao;

import java.util.List;
/***
 * This ia an interface for methods to implement crud services and also obtain characteristics for the order
 * @author tolaa
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

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
