package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

//import com.qa.controller.CustomerController;
import com.qa.controller.OrderController;
//import com.qa.controller.ItemController;
import com.qa.persistence.domain.Customer;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Order;
import com.qa.utils.Config;
public class OrderDaoMysql implements OrderDao<Order,Item,Customer> {
	public static final Logger logger = Logger.getLogger(OrderController.class);
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
	@Override
	public List<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			checkConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
			while (resultSet.next()) {
				Long id = (long) resultSet.getInt("id");
				Long custId = (long) resultSet.getInt("custid");
				Double price  = resultSet.getDouble("price");
				Order order = new Order(id,custId,price);
				orders.add(order);
			}
			
		} catch (Exception e) {
			logger.error("error displaying the list of orders");
		}
		return orders;
	}

	public Order create(Order order) {
		try {
			checkConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO orders(custid,price) values('" + order.getCustId() + "','" + order.getPrice()+ "')" );
			logger.info("Order created");
		} catch (Exception e) {
			logger.error(e);
		}
		return null ;
		
	}
	@Override
	public Order update(long id, Order t) {
		Long orderId = (Long)id;
		
		String sql = "UPDATE orders SET price= ? WHERE id=" + orderId + " and custid ="+ t.getCustId();
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, t.getPrice());
			
			stmt.execute();
			if(orderId==0) {
				logger.error("This order does not exist in the order table");
			}else {
				logger.info("Update complete");
			}
			connection.close();
	}catch(Exception e) {
		 logger.error(e);
		 
	 }
		return null;
		
	}
	@Override
	public void delete(Order order) {
		String sql = "DELETE FROM orders WHERE id= ?";
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, order.getId());
			stmt.execute();
			System.out.println("Delete complete ");
			connection.close();
	}catch(Exception e) {
		 logger.error("Delete failed");
		 
	 }
	}
		
	
@Override 
  public List<Item> itemsDisplay () {
	  ArrayList<Item> items = new ArrayList<Item>();
		try {
			checkConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
			while (resultSet.next()) {
				Long id = (long) resultSet.getInt("id");
				String itemName = resultSet.getString("itemname");
				Double price = resultSet.getDouble("price");
				Long quantity = (long) resultSet.getInt("quantity");
				Item item = new Item(id, itemName, price, quantity);
				items.add(item);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return items;
	  
  }
@Override
  public Double itemsPrice(Item c) {
	  String sql = "SELECT price FROM items WHERE itemname =?";
	  Double price = (double)0;
	  try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getName());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
				 price = rs.getDouble("price");
				
			}
			logger.info("price obtained");
			rs.close();
			connection.close();
	}catch(Exception e) {
		 logger.error(e);
		 price= (double) 0;
		 
	 }
	 return price;
  }

@Override
public Long getCustomerId(Customer v) {
	String sql = "SELECT id FROM customers WHERE firstname= ? AND surname= ? AND email= ?";
	Long id =(long) 0;
	try {
		checkConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, v.getFirstName());
		stmt.setString(2, v.getSurname());
		stmt.setString(3, v.getEmail());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			 id = (long) rs.getInt("id");
			
		}if(id==0) {
			logger.error("This customer does not exist in the database");
		}else {
			logger.info("Customerid obtained");
		}
		rs.close();
		connection.close();
}catch(Exception e) {
	 logger.error(e);
	 id= (long) 0;
	 
 }
	logger.info("The customerid is "+id);
	return id;
}
@Override
public Long getOrderId(Long custId, Double price) {
	String sql = "SELECT id from orders WHERE custid= ? && price= ? ";
	Long id =(long) 0;
	try {
		checkConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, custId);
		stmt.setDouble(2,price);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			 id = (long) rs.getInt("id");
			
		}
		if(id==0) {
			logger.error("This order does not exist in the database");
		}else {
			logger.info("Orderid obtained");
		}
		rs.close();
		connection.close();
}catch(Exception e) {
	 logger.error(e);
	 id= (long) 0;
	 
 }
	logger.info("The orderid is "+id);
	return id;
	
}
@Override
public Long getItemId(Item u) {
	String sql = "SELECT id from items WHERE itemname= ?";
	long id =(long) 0;
	try {
		checkConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, u.getName());
	
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			 id = (long) rs.getInt("id");
			
		}
		if(id==0) {
			logger.error("This item does not exist in the database");
		}else {
			logger.info("Itemid obtained");
		}
		rs.close();
		connection.close();
}catch(Exception e) {
	 logger.error(e);
	 id= (long) 0;
	 
 }
	logger.info("The itemid is "+id);
	return id;
}
@Override
public List<Order> orderDetailsDisplay(Long id) {
	 ArrayList<Order> orders = new ArrayList<Order>();
		try {
			checkConnection();
			String sql= "SELECT * FROM orders WHERE custid =? ";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet resultSet = stmt.executeQuery();
			
			while (resultSet.next()) {
				Long idGet = (long) resultSet.getInt("id");
				Long custId = resultSet.getLong("custid");
				Double price = resultSet.getDouble("price");
				Order order = new Order(idGet, custId, price);
				orders.add(order);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return orders;
}
@Override
public Double getTotalPriceById(Long orderId) {
	String sql = "SELECT price FROM orders WHERE id= ?";
	Double price =0.00;
	try {
		checkConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, orderId);
	
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			 price = (double) rs.getDouble("price");
			
		}
		if(price==0.00) {
			logger.error("This price does not exist in the order table");
		}else {
			logger.info("Price obtained");
		}
		rs.close();
		connection.close();
}catch(Exception e) {
	 logger.error(e);
	 price= 0.00;
	 
 }
	logger.info("The price of the order is "+price);
	return price;
}
	
	
}
