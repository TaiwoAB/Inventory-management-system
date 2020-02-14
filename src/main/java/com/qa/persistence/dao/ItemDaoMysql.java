package com.qa.persistence.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controller.ItemController;
import com.qa.persistence.domain.Item;
import com.qa.utils.Config;

public class ItemDaoMysql implements ItemDao<Item> {
	public static final Logger logger = Logger.getLogger(ItemController.class);
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

	public List<Item> readAll() {
		ArrayList<Item> items = new ArrayList<Item>();
		try  {
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

	public Item create(Item item) {
		try{
			checkConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into items(itemname, price, quantity) values('" + item.getName() + "','" + item.getPrice()+ "','"+ item.getQuantity()+ "')" );
			logger.info("Item created");
		} catch (Exception e) {
			logger.error(e);
		}
		return null; 
	}
public Long getItemId(Item c) {
	String sql = "SELECT id FROM items WHERE itemname= ?";
	Long id =(long) 0;
	try {
		checkConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, c.getName());
	
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
	 logger.error("Itemid not obtained");
	 id= (long) 0;
	 
 }
	logger.info("The itemid is "+id);
	return id;
}
	public Item update(Long id, Item item) {
		Long itemId = (Long)id;
		
			String sql = "UPDATE items SET itemname= ?, price= ?, quantity= ? WHERE id=" + itemId  ;
			try {
				checkConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, item.getName());
				stmt.setDouble(2, item.getPrice());
				stmt.setInt(3, item.getQuantity().intValue());
				stmt.execute();
				if(itemId==0) {
					logger.error("This item does not exist in the database");
				}else {
					logger.info("Update of item complete");
				}
				connection.close();
		}catch(Exception e) {
			 logger.error(e);
			 
		 }
			return null;
		

	}

	public void delete(Item item) {
		
		String sql = "DELETE FROM items WHERE itemname= ?";
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, item.getName());
			stmt.execute();
			System.out.println("Delete item complete ");
			connection.close();
	}catch(Exception e) {
		 logger.error(e);
		 
	 }
	}

}
