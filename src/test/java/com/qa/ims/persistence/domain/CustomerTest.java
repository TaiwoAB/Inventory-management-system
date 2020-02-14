package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Customer;

public class CustomerTest {
	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1L, "Taiwo", "AB","ta@gmail.com");
		other = new Customer(1L, "Taiwo", "AB","ta@gmail.com");
		
	}
	
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		assertNotNull(customer.getEmail());
		customer.setId(null);
		assertNull(customer.getId());
		customer.setFirstName(null);
		assertNull(customer.getFirstName());
		customer.setSurname(null);
		assertNull(customer.getSurname());
		
	}
	
	
	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(customer.equals(new Object()));
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, customer.getId(), 0);
		assertEquals("Taiwo", customer.getFirstName());
		assertEquals("AB", customer.getSurname());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(customer.equals(customer));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void customerNameNullButOtherNameNotNull() {
		customer.setFirstName(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void customerNamesNotEqual() {
		other.setFirstName("rhys");
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsName() {
		customer.setFirstName("Taiwo");
		other.setFirstName("Taiwo");
		assertTrue(customer.getFirstName().equals(other.getFirstName()));
	}

	@Test
	public void sameId() {
//		if(other.getId()==customer.getId()) {
//			bool=true
//		}
		assertTrue(other.getId()==customer.getId());
	}
	
	@Test
	public void sameIdOnBoth() {
		customer.setId((1L));
		other.setId((1L));
		assertTrue(other.getId()==customer.getId());
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2l);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullSurname() {
		customer.setSurname(null);
		assertFalse(customer.equals(other));
	}
	
	@Test
	public void nullSurnameOnBoth() {
		customer.setSurname("Taiwo");
		other.setSurname("Taiwo");
		assertTrue(customer.getSurname().equals(other.getSurname()));
	}
	
	@Test
	public void otherSurnameDifferent() {
		other.setSurname("AB");
		assertTrue(customer.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Customer customer = new Customer("Taiwo", "AB", "ta@gmail.com");
		assertNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		assertNotNull(customer.getEmail());
	}
	
	
	@Test
	public void toStringTest() {
		
		String toString = "\n"+ "Id: 1"  + "\n"+ "First name: Taiwo"  +"\n"+ "Surname: AB" +"\n"+ "Email: ta@gmail.com" + "\n" + "....................................................";
		assertEquals(toString, customer.toString());
	}
	
}
