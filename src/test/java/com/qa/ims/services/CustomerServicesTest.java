package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.persistence.dao.Dao;
import com.qa.persistence.domain.Customer;
import com.qa.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {
	
	@Mock
	private Dao<Customer> customerDao;
	
	@InjectMocks
	private CustomerServices customerServices;
	
	@Test
	public void customerServicesCreate() {
		Customer customer = new Customer("Taiwo", "AB", "tolaainabadejo@yahoo.co.uk");
		customerServices.create(customer);
		Mockito.verify(customerDao, Mockito.times(1)).create(customer);
	}
	
	@Test
	public void customerServicesRead() {
		customerServices.readAll();
		Mockito.verify(customerDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void customerServicesUpdate() {
		Customer customer = new Customer("Taiwo", "AB", "tolaainabadejo@yahoo.co.uk");
		customerServices.update(1L,customer);
		Mockito.verify(customerDao, Mockito.times(1)).update(1L, customer);
	}
	
	@Test
	public void customerServicesDelete() {
		Customer customer = new Customer("Taiwo", "AB", "tolaainabadejo@yahoo.co.uk");
		customerServices.delete(customer);
		Mockito.verify(customerDao, Mockito.times(1)).delete(customer);
	}
}
