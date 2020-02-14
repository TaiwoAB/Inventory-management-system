package com.qa.services;

import java.util.List;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Customer;

public class CustomerServices implements CrudServices<Customer>, GetCustomerId<Customer>{

	Dao<Customer> customerDao;
	
	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public List<Customer> readAll() {
		return customerDao.readAll();
	}
	@Override
	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}
	@Override
	public Customer update(Long id, Customer t) {
		
		return customerDao.update(id, t);
	}
	@Override
	public void delete(Customer t) {
		customerDao.delete(t);
	}
	@Override
	public Long getCustomerId(Customer t) {
	
		return customerDao.getCustomerId(t);
	}

}
