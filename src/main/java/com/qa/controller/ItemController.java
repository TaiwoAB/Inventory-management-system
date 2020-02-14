package com.qa.controller;
/**
 * Importing what is required for this class
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Item;
import com.qa.services.CrudServices;

import com.qa.services.GetItemId;
import com.qa.utils.Utils;
/**
 * This class is the item controller which implements interfaces such as the CRUD controller and the GetItemIdController
 * @author tolaa
 *
 */

public class ItemController implements CrudController<Item>, GetItemIdController<Item>{
	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	/**
	 * Intialising the class variables
	 */
	private CrudServices<Item> itemService;
	private GetItemId<Item> getItemId;
	/**
	 * Creating a constructor for this class
	 * @param itemService
	 * @param getItemId
	 */
	public ItemController(CrudServices<Item> itemService,GetItemId<Item> getItemId ) {
		this.itemService = itemService;
		this.getItemId=getItemId;
	}
	/**
	 * Creating a constructor for this class
	 * @param itemService
	 * @param getItemId
	 */
	
	/***
	 * Creating a method for invoke the read method of the customer service to list all items in the database
	 */
	public List<Item> readAll() {
		List<Item> items = new ArrayList<Item>();
		for(Item item: itemService.readAll()) {
			LOGGER.info(item.toString());
			items.add(item);
		}
		return items;
	}
	/***
	 * Creating a method for invoke the create method of the customer service to create items in the database
	 */
	public Item create() {
		LOGGER.info("Please enter item name");
		String itemName = Utils.getInput(); 
		while(itemName.matches(".*\\d.*")) {
			LOGGER.info("Please enter item name");
			itemName = Utils.getInput();
		}
		LOGGER.info("Please enter the item price");
		String price= Utils.getInput(); 
		while(price.matches(".*[A-Z a-z].*")) {
			LOGGER.info("Please enter the item price");
			price = Utils.getInput();
		}
		Double itemPrice = Double.parseDouble(price);
		LOGGER.info("Please enter the item quantity");
		String quantity = Utils.getInput();  ;
		while(quantity.matches(".*[A-Z a-z].*")) {
			LOGGER.info("Please enter the item quantity");
			quantity = Utils.getInput();
		}
		Long itemQuantity = Long.parseLong(quantity);
		
		Item item= itemService.create(new Item(itemName,itemPrice,itemQuantity));
		return item;
		
	}
	/***
	 * Creating a method for invoke the update method of the customer service to update items in the database
	 */
	public Item update() {
		Item item= null;
		LOGGER.info("Hi, it seems you want to change some details, please follow the steps below");
		Long itemId= getItemId();
		if(itemId!=0) {
		LOGGER.info("Please enter new or current item name");
		String itemName = Utils.getInput(); 
		while(itemName.matches(".*\\d.*")) {
			LOGGER.info("Please enter new or current item name");
			itemName = Utils.getInput();
		}
		LOGGER.info("Please enter the new or current item price");
		String price= Utils.getInput(); 
		while(price.matches(".*[A-Z a-z].*")) {
			LOGGER.info("Please enter the new or current item price");
			price = Utils.getInput();
		}
		Double itemPrice = Double.parseDouble(price);
		LOGGER.info("Please enter the new or current item quantity");
		String quantity = Utils.getInput();  ;
		while(quantity.matches(".*[A-Z a-z].*")) {
			LOGGER.info("Please enter the new or current item quantity");
			quantity = Utils.getInput();
		}
		Long itemQuantity = Long.parseLong(quantity);
		
		
		item = itemService.update(itemId, new Item(itemName,itemPrice,itemQuantity));
		}
		return item;
	}
	/***
	 * Creating a method for invoke the delete method of the customer service to delete items in the database
	 */
	public void delete() {
		LOGGER.info("Please enter the item name");
		String name = Utils.getInput();
		while( name.matches(".*\\d.*")) {
			LOGGER.info("Please enter item name");
			 name = Utils.getInput();
		}
		itemService.delete(new Item(name));
	}
	/***
	 * Creating a method for invoke the delete method of the customer service to delete items in the database
	 */
	public Long getItemId() {
		LOGGER.info("Please enter the name");
		String itemName = Utils.getInput();
		while(itemName.matches(".*\\d.*")) {
			LOGGER.info("Please enter item name");
			itemName = Utils.getInput();
		}
		Long itemId = getItemId.getItemId(new Item(itemName));
	  return itemId;
		
	}


	
}
