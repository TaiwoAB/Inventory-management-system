//package com.qa.ims.services;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.qa.persistence.dao.OrderDao;
//import com.qa.persistence.domain.Customer;
//import com.qa.persistence.domain.Item;
//import com.qa.persistence.domain.Order;
//import com.qa.services.OrderServices;
//
//public class OrderServicesTest {
//	@RunWith(MockitoJUnitRunner.class)
//	public class orderServicesTest {
//		@Mock
//		private OrderDao<Order, Item, Customer> orderDao;
//		@InjectMocks
//		private OrderServices orderServices;
//		@Test
//		public void orderServicesCreate() {
//			Order order = new Order (17.99, 1L);
//			orderServices.create(order);
//			Mockito.verify(orderDao, Mockito.times(1)).create(order);
//		}
//		@Test
//		public void orderServicesRead() {
//			orderServices.readAll();
//			Mockito.verify(orderDao, Mockito.times(1)).readAll();
//		}
//		@Test
//		public void itemsServicesUpdate() {
//			Order order = new Order(17.99, 1L);
//			orderServices.update(1L, order);
//			Mockito.verify(orderDao, Mockito.times(1)).update(1L,order);
//		}
//		@Test
//		public void itemsServicesDelete() {
//			Order order = new Order(17.99, 1L);
//			orderServices.delete(order);
//			Mockito.verify(orderDao, Mockito.times(1)).delete(order);
//		}
//	}
//}
