package com.qa.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.services.CrudServices;
import com.qa.services.GetCustomerId;
import com.qa.utils.Utils;

public class CustomerController implements CrudController<Customer>, GetCustomerIdController<Customer>{

	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Customer> customerService;
	private GetCustomerId<Customer> getCustomerId;
	
	public CustomerController(CrudServices<Customer> customerService, GetCustomerId<Customer> getCustomerId ) {
		this.customerService = customerService;
		this.getCustomerId = getCustomerId;
		
	}
	
	public List<Customer> readAll() {
		List<Customer> customers = new ArrayList<Customer>();
		for(Customer customer: customerService.readAll()) {
			LOGGER.info(customer.toString());
			customers.add(customer);
		}
		return customers;
	}


	public Customer create() {
		LOGGER.info("Please enter a first name");
		String firstName = Utils.getInput();
		while(firstName.matches(".*\\d.*")) {
			LOGGER.info("Please enter a first name");
			firstName = Utils.getInput();
		}
		LOGGER.info("Please enter a surname");
		String surname = Utils.getInput();
		while(surname.matches(".*\\d.*")) {
			LOGGER.info("Please enter a surname");
			surname = Utils.getInput();
		}
        LOGGER.info("Please enter your email");
		String email = Utils.getInput();
		while(!email.contains("@") || email.indexOf('@')<1) {
			LOGGER.info("Please enter your email");
			email = Utils.getInput();
		}
		Customer customer = customerService.create(new Customer(firstName, surname, email));
		return customer;
		
	}


	public Customer update() {
		Customer customer=null;
		LOGGER.info("Hi,  it seems you want to change some details, please follow the steps below");
		Long custId = getCustomerId();
		if(custId!=0) {
			LOGGER.info("Please enter the new details");
			LOGGER.info("Please enter the new or currently used first name for update");
			String newOrCurrFirstName = Utils.getInput();
			while(newOrCurrFirstName.matches(".*\\d.*")) {
				LOGGER.info("Please enter a first name");
				newOrCurrFirstName = Utils.getInput();
			}
			LOGGER.info("Please enter the new or currently used surname for update");
			String newOrCurrSurname = Utils.getInput();
			while(newOrCurrSurname .matches(".*\\d.*")) {
				LOGGER.info("Please enter the new or currently used surname for update");
				newOrCurrSurname  = Utils.getInput();
			}
			LOGGER.info("Please enter the new or currently used email for update");
			String newOrCurrEmail = Utils.getInput();
			while(!newOrCurrEmail.contains("@") || newOrCurrEmail.indexOf('@')<1) {
				LOGGER.info("Please enter the new or currently used email for update");
				newOrCurrEmail = Utils.getInput();
			}
			  customer = customerService.update(custId, new Customer(newOrCurrFirstName, newOrCurrSurname, newOrCurrEmail));
			
		}
		 return customer;
	
	}

	public void delete() {
		LOGGER.info("Please enter a first name");
		String firstName = Utils.getInput();
		while(firstName.matches(".*\\d.*")) {
			LOGGER.info("Please enter a first name");
			firstName = Utils.getInput();
		}
		LOGGER.info("Please enter a surname");
		String surname = Utils.getInput();
		while(surname.matches(".*\\d.*")) {
			LOGGER.info("Please enter a surname");
			surname = Utils.getInput();
		}
		LOGGER.info("Please enter a email");
		String email = Utils.getInput();
		while(!email.contains("@") || email.indexOf('@')<1) {
			LOGGER.info("Please enter your email");
			email = Utils.getInput();
		}
		customerService.delete(new Customer(firstName, surname, email));
	}

	
	public Long getCustomerId() {
		
		LOGGER.info("Please enter the currently used first name");
		String currFirstName = Utils.getInput();
		while(currFirstName.matches(".*\\d.*")) {
			LOGGER.info("Please enter a first name");
			currFirstName = Utils.getInput();
		}
		LOGGER.info("Please the currently used surname");
		String currSurname = Utils.getInput();
		while(currSurname .matches(".*\\d.*")) {
			LOGGER.info("Please enter the new or currently used surname for update");
			currSurname  = Utils.getInput();
		}
		LOGGER.info("Please the currently used email");
		String currEmail = Utils.getInput();
		while(!currEmail.contains("@") || currEmail.indexOf('@')<1) {
			LOGGER.info("Please enter your email");
			currEmail = Utils.getInput();
		}
		Long custId = getCustomerId.getCustomerId(new Customer(currFirstName, currSurname, currEmail));
	  return custId;
		
	}

	public String getInput() {
		// TODO Auto-generated method stub
		return Utils.getInput();
	}
	
}
