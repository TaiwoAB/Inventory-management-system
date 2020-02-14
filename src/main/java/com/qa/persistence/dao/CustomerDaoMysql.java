package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controller.CustomerController;
import com.qa.persistence.domain.Customer;
import com.qa.utils.Config;
/**
 * Class for executing SQL statments to the database
 * @author tolaa
 *
 */
public class CustomerDaoMysql implements Dao<Customer> {
	
	public static final Logger logger = Logger.getLogger(CustomerController.class);
	private Connection connection;
	
	public String checkConnection() {
	 try {
		 this.connection= DriverManager.getConnection("jdbc:mysql://35.246.124.49:3306/IMS", Config.username, Config.password); 
		 return "Connection passed";
	 }catch(Exception e) {
		 logger.error(e);
		 return "Connection failed";
	 } 		
		
	}
		/**
		 * This method is used to list all the customers in the database
		 */

	@SuppressWarnings("finally")
	public List<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Statement statement= null;
		try {
			checkConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
			while (resultSet.next()) {
				Long id = (long) resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String surname = resultSet.getString("surname");
				String email = resultSet.getString("email");
				Customer customer = new Customer(id, firstName, surname, email);
				customers.add(customer);
				statement.closeOnCompletion();
			}
		} catch (Exception e) {
			logger.error(e);
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				logger.error(e);
			}
			return customers;
		}
			
	}
		


	@SuppressWarnings("finally")
	public Customer create(Customer customer) {
		Statement statement = null;
		try{
			
			checkConnection();
			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO customers(firstname, surname, email) values('" + customer.getFirstName() + "','" + customer.getSurname()+ "','"+ customer.getEmail()+ "')" );
			logger.info("Customer created");
			connection.close();
		} catch (Exception e) {
			logger.error(e);
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				logger.error(e);
			}
			return null; 
		}
			
		
	}
/**
 * This method retrieves the customer id from the database
 */
@SuppressWarnings("finally")
public Long getCustomerId(Customer c) {
	PreparedStatement stmt=null;
	String sql = "SELECT id from customers WHERE firstname= ? && surname= ? && email= ?";
	Long id =(long) 0;
	try {
		checkConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, c.getFirstName());
		stmt.setString(2, c.getSurname());
		stmt.setString(3, c.getEmail());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			 id = (long) rs.getInt("id");
			
		}
		if(id==0) {
			logger.error("This customer does not exist in  the database");
		}else {
			logger.info("Customerid obtained");
		}
		
		rs.close();
		connection.close();
}catch(Exception e) {
	 logger.error(e);
	 id= (long) 0;
	 
 }finally {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info("The customerid is "+id);
		return id;
	}
	
}
/**
 * This method updates the customer in the database
 */
	@SuppressWarnings("finally")
	public Customer update(Long id, Customer customer) {
		PreparedStatement stmt= null;
		Long custId = (long)id;
		String sql = "UPDATE customers SET firstname= ?, surname= ?, email= ? WHERE id=" + custId  ;
		try {
			checkConnection();
		    stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getSurname());
			stmt.setString(3, customer.getEmail());
			stmt.execute();
			if(custId==0) {
				logger.error("This customer does not exist in the database");
			}else {
				logger.info("Update complete");
			}
			connection.close();
	}catch(Exception e) {
		 logger.error(e);
		 
	 }finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				logger.error(e);
			}
			return null;
		}

	}
/**
 * This method is used to delete a customer in the database
 */
	public void delete(Customer customer) {
		PreparedStatement stmt=null;
		String sql = "DELETE FROM customers WHERE firstname= ? && surname= ? && email= ?";
		try {
			checkConnection();
		    stmt = connection.prepareStatement(sql);
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getSurname());
			stmt.setString(3, customer.getEmail());
			stmt.execute();
			System.out.println("Delete complete ");
			connection.close();
	}catch(Exception e) {
		 logger.error(e);
		 
	 }finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				logger.error(e);
			}
			
		}
	}







	
}



