package com.qa.controller;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;

import com.qa.persistence.domain.Customer;
import com.qa.persistence.domain.Item;
import com.qa.persistence.domain.Order;
import com.qa.persistence.domain.OrderLine;
import com.qa.services.CrudServices;
import com.qa.services.GetCustomerId;
import com.qa.services.GetItemId;
import com.qa.services.GetOrderDetails;
import com.qa.services.OrderLineServices;
import com.qa.utils.Utils;

public class OrderController implements CrudController<Order>,GetItemListController<Item>,GetCustomerIdController<Customer>, GetItemIdController<Item>, GetOrderIdController<Order>, GetOrderItemDetailsController{
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	private String itemName;
	private CrudServices<Order> orderService;
	public GetCustomerId<Customer> getCustomerId;
	public GetItemId<Item> getItemId;
	private GetOrderDetails<Order,Item,Customer> getOrderDetails;
	private OrderLineServices orderLineService;
	
	
	
	
	public OrderController(CrudServices<Order> orderService, GetCustomerId<Customer> getCustomerId, GetOrderDetails<Order, Item,Customer> getOrderDetails, GetItemId<Item> getItemId,OrderLineServices orderLineService) {
		this.orderService= orderService;
		this.getCustomerId = getCustomerId;
		this.getItemId = getItemId;
		this.getOrderDetails = getOrderDetails;
		this.orderLineService= orderLineService;
	}
	
	public List<Order> readAll() {
		List<Order> orders = new ArrayList<Order>(); 
		for(Order order: orderService.readAll()) {
			LOGGER.info(order.toString());
			orders.add(order);
		}
		return orders;
	}
	


	public Order create() {
		ArrayList<Long> idList = new ArrayList<Long>();
		ArrayList<Integer> quantityList = new ArrayList<Integer>();
     	Double totalPrice = 0.00;
		Long custId= getCustomerId();
		itemsDisplay();
		while(true) {
			
			LOGGER.info("Please enter the name of the item to purchase. Enter stop to end.");
			 this.itemName = Utils.getInput();
			if(this.itemName.equalsIgnoreCase("stop")){
				break;
			}
			Long id= getItemId();
			idList.add(id);
			
			Double price =getOrderDetails.itemsPrice(new Item(itemName));
			LOGGER.info("Please enter the quantity");
			String quantity = Utils.getInput();
			int quantityinteger = Integer.parseInt(quantity);
			quantityList.add( quantityinteger);
			Double totalPriceForEachItem = quantityinteger * price;
			totalPrice = totalPrice + totalPriceForEachItem;
			
			
		}
		
		Order order =orderService.create(new Order(totalPrice,custId));
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		DecimalFormat df = new DecimalFormat("0.00");      
		Double total = Double.parseDouble(df.format(totalPrice));
		Long result= getOrderId(custId,total);
		
		if(result!=0) {
			for(int i=0; i<quantityList.size();i++) {
				System.out.println("list of id"+idList.get(i));
				System.out.println("list of quantity"+quantityList.get(i));
			
				orderLineCreate(result,idList.get(i),quantityList.get(i));
			}
			
		}
		return order;
		
	}

	public  Order update() {
		Order orders = null;
		String name= "";
		Long custId= getCustomerId();
		if(custId!=0) {
			for(Order order:orderDetailsDisplay(custId)) { ///This uses the orderdetailsdisplay fucntion to return the list of orders and print each one
				LOGGER.info(order.toString());
			}
			LOGGER.info("Please enter the orderid you want to change from the list");
			String strordId= Utils.getInput();
			Long ordId= Long.parseLong(strordId);
			System.out.println(ordId);
			Double totalPrice= getTotalPriceById(ordId);
			System.out.println(ordId);
			readAllOrderLine(ordId);
			while(true) {
				LOGGER.info("Please enter the orderlineid you want to change from the list Enter stop to end");
				String answer= Utils.getInput();
				if(answer.equalsIgnoreCase("stop")) {
					break;
				}
				Long orderlineid= Long.parseLong(answer);
				LOGGER.info("Please enter the item quantity you want to change from the list");
				String qty= Utils.getInput();
				int quantity= Integer.parseInt(qty);
				while(quantity<0) {
					LOGGER.info("Please enter the item quantity you want to change from the list");
					 qty= Utils.getInput();
				    quantity= Integer.parseInt(qty);
				}
				
				orderLineUpdate(orderlineid, quantity);
				name=getItemIdInnOrderline(orderlineid);
				if(!name.equals("")) {
					Double pricePerItem= getOrderDetails.itemsPrice(new Item(name));
					totalPrice=totalPrice-(quantity*pricePerItem);
					if((quantity*pricePerItem)<totalPrice) {
						totalPrice=totalPrice-(quantity*pricePerItem);
					}else if((quantity*pricePerItem)==totalPrice) {
						totalPrice=totalPrice+0;
					}else {
						totalPrice=totalPrice+(quantity*pricePerItem);
					}
					orderLineUpdate(orderlineid ,quantity);
					 orders= orderService.update(ordId,new Order(ordId,custId,totalPrice));
					
				}
				
				
				
			}
			
		}
		
		return orders;
		
		
	}

	public void delete() {
		Long custId= getCustomerId();
		
		for(Order order:orderDetailsDisplay(custId)) { ///This uses the orderdetailsdisplay fucntion to return the list of orders and print each one
			LOGGER.info(order.toString());
		}
		while(true) {
			LOGGER.info("Please enter the id of the order sabyou want to delete, please enter stop to end");
			String id = Utils.getInput();
			if(id.equalsIgnoreCase("stop")){
				break;
			}
			Long orderId = Long.parseLong(id);
			orderService.delete(new Order(orderId));
		}
			
	}

	public Long getCustomerId() {
		
				LOGGER.info("Please enter your first name");
				String firstName = Utils.getInput();
				while(firstName.matches(".*\\d.*")) {
					LOGGER.info("Please enter a first name");
					firstName = Utils.getInput();
				}
				LOGGER.info("Please enter your surname");
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
				Long custId = getCustomerId.getCustomerId(new Customer(firstName, surname, email));
			  return custId;
	}
	
	public Long getOrderId(Long custId, Double price) {
		Long  orderId =  getOrderDetails.getOrderId(custId, price);
	
		return orderId;
	}
	
	public Long getItemId() {
		
		Long itemId = getItemId.getItemId(new Item(itemName));
	  return itemId;
		
	}

	public void itemsDisplay() {
		for(Item item: getOrderDetails.itemsDisplay()) {
			LOGGER.info(item.toString());
		}
		
		
	}



	public List<Order> orderDetailsDisplay(Long id) {
		List<Order> orders = new ArrayList<Order>();
		orders = getOrderDetails.orderDetailsDisplay(id);
		return orders;
	}

	public void readAllOrderLine(Long orderId) {
	
		for(OrderLine orderLine: orderLineService.readAllOrderLine(orderId)) {
			LOGGER.info(orderLine.toString());
			}
	}

	public void orderLineCreate(Long orderId, Long itemId, int quantity) {
		
		orderLineService.orderLineCreate(orderId, itemId, quantity);
	}

	public void orderLineUpdate(Long id , int quantity) {
	
		orderLineService.orderLineUpdate(id, quantity);
	}

	public void orderLineDelete(Long id ){
	
		orderLineService.orderLineDelete(id);
		
	}

	public Double getTotalPriceById(Long orderId) {
		return getOrderDetails.getTotalPriceById(orderId);
	}

	public String getItemIdInnOrderline(Long id) {
	
		return orderLineService.getItemIdInnOrderline(id);
	}



	
	
	
	


}
