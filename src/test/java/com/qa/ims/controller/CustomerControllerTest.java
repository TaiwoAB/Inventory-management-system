package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.controller.CustomerController;
import com.qa.persistence.domain.Customer;
import com.qa.services.CustomerServices;



@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	/**
	 *  The thing I want to fake functionlity for
	 */
	@Mock
	private CustomerServices customerServices;
	
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices, customerServices);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Chris", "P"));
		customers.add(new Customer("Rhys", "T"));
		customers.add(new Customer("Nic", "J"));
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	@Test
	public void createTest() {
		String firstName = "Chris";
		String surname = "Perrins";
		String email= "ta@gmail.com";
		Mockito.doReturn(firstName, surname, email).when(customerController).getInput();
		Customer customer = new Customer(firstName, surname,email);
		Customer savedCustomer = new Customer( "Chris", "Perrins","ta@gmail.com" );
		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		String id = "2";
		String firstName = "Tolj";
		String surname = "Thompson";
		Mockito.doReturn(id, firstName, surname).when(customerController).getInput();
		Customer customer = new Customer(1L, firstName, surname);
		Mockito.when(customerServices.update(2L, customer)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}
	

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Customer customer = new Customer("Taiwo", "AB");
			Mockito.doReturn(id).when(customerController).getInput();
			customerController.delete();
			Mockito.verify(customerServices, Mockito.times(1)).delete(customer);
		
	}
	
}
