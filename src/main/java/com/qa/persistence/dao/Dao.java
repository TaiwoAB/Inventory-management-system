package com.qa.persistence.dao;

import java.util.List;


public interface Dao<T> {

    List<T> readAll();
     
    T create(T t);
     
    T update(Long id, T t);
     
    void delete(T t);
    
    Long getCustomerId(T t);
 
}
	