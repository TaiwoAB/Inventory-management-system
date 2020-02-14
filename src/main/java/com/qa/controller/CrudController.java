package com.qa.controller;

import java.util.List;
/**
 * 
 * @author tolaa
 *Crating the crud controller interface which contains method for obtaining creating, reading, updating and deleting.
 * @param <T>
 */
public interface CrudController<T> {
    
    List<T> readAll();
     
    T create();
     
    T update();
     
    void delete();

}
