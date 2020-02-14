package com.qa.persistence.dao;

import java.util.List;


public interface ItemDao<T> {
	List<T> readAll();
    
    T create(T t);
     
    T update(Long id, T t);
     
    void delete(T t);
    
    Long getItemId(T t);
}
