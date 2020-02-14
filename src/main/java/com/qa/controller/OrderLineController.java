package com.qa.controller;

import org.apache.log4j.Logger;
import com.qa.persistence.domain.OrderLine;

import com.qa.services.GetOrderItemDetails;


	
	public class OrderLineController implements GetOrderItemDetailsController {
		
		public static final Logger LOGGER = Logger.getLogger(OrderLineController.class);
	private GetOrderItemDetails<OrderLine> orderLineService;

		
		
		public OrderLineController(GetOrderItemDetails<OrderLine> orderLineService) {
			this.orderLineService= orderLineService;
			
		}



		public void readAllOrderLine(Long orderId) {
		
			for(OrderLine orderLine: orderLineService.readAllOrderLine(orderId)) {
				LOGGER.info(orderLine.toString());
			}
		}

		

		public void orderLineCreate(Long orderId, Long itemId, int quantity) {
	
			orderLineService.orderLineCreate(orderId, itemId, quantity);;
			
		}

		public void orderLineUpdate(Long id, int quantity) {
			
			orderLineService.orderLineUpdate(id, quantity);
		}


		public void orderLineDelete(Long id) {
		
			orderLineService.orderLineDelete(id);
		}




		public String getItemIdInnOrderline(Long id) {
		
			return orderLineService.getItemIdInnOrderline(id);
		}




	}
