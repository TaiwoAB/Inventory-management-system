package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controller.OrderLineController;
import com.qa.persistence.domain.OrderLine;
import com.qa.utils.Config;

public class OrderLineDaoMysql implements OrderLineDao<OrderLine> {

	public static final Logger logger = Logger.getLogger(OrderLineController.class);
	private Connection connection;

	public String checkConnection() {
		try {
			 this.connection= DriverManager.getConnection("jdbc:mysql://35.246.124.49:3306/IMS", Config.username, Config.password); 
			return "Connection passed";
		} catch (Exception e) {
			logger.error(e);
			return "Connection failed";
		}

	}

	@Override
	public List<OrderLine> readAllOrderLine(Long orderId) {
		
		Long ordId = orderId;
		List<OrderLine> orderLineList = new ArrayList<OrderLine>();
		try  {
			checkConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orderline where orderid =" + ordId);
			while (resultSet.next()) {
				Long id = (long) resultSet.getInt("id");
				Long itemId = (long) resultSet.getInt("itemid");
				int quantity = resultSet.getInt("itemquantity");
				OrderLine orderLine = new OrderLine(id, ordId, itemId, quantity);
				orderLineList.add(orderLine);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return orderLineList;

	}

	@Override
	public void orderLineCreate(Long orderId, Long itemId, int quantity) {
		try {
			checkConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into orderline(orderid, itemid, itemquantity) values('" + orderId + "','"
					+ itemId + "','" + quantity + "')");
			logger.info("Orderline created");
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Override
	public void orderLineUpdate(Long id, int quantity) {

		String sql = "UPDATE items SET quantity= ? WHERE id=" + id;
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.execute();
			if (id == 0) {
				logger.error("This orderline does not exist in the orderline table");
			} else {
				logger.info("Update orderline complete");
			}
			//connection.close();
		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Override
	public void orderLineDelete(Long id) {
	
		String sql = "DELETE FROM Orders WHERE id= ?";
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id.intValue());
			stmt.execute();
			System.out.println("Delete of orderline complete ");
			connection.close();
		} catch (Exception e) {
			logger.error(e);

		}
	}

	@Override
	public String getItemIdInnOrderline(Long id) {
		String name = "";
		String sql = "SELECT itemid FROM orderline WHERE id= ?";
		Long itemId = (long) 0;
		try {
			checkConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				itemId = (long) rs.getInt("itemid");

			}
			if (itemId == 0) {
				logger.error("This item does not exist in the item table");

			} else {
				logger.info("Itemid obtained");
			
				sql = "SELECT itemname FROM items WHERE id= ?";
				try {
					checkConnection();
					PreparedStatement statmt = connection.prepareStatement(sql);
					statmt.setLong(1, itemId);

					ResultSet rse = statmt.executeQuery();
					while (rse.next()) {
						name = rse.getString("itemname");
						System.out.println(name);
					}
					if (!name.equals("")) {
						logger.info("Item name obtained");
					}

				} catch (Exception e) {
					logger.error(e);
				}
				rs.close();
				connection.close();
			}
		} catch (Exception e) {
			logger.error(e);
			itemId = (long) 0;

		}
		logger.info("The item name is " + name);
		return name;

	}

}
