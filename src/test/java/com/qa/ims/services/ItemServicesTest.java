package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.persistence.dao.ItemDao;
import com.qa.persistence.domain.Item;
import com.qa.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {
	@Mock
	private ItemDao<Item> itemDao;
	@InjectMocks
	private ItemServices itemsServices;
	@Test
	public void itemsServicesCreate() {
		Item item = new Item("milk", 5.99, 17L);
		itemsServices.create(item);
		Mockito.verify(itemDao, Mockito.times(1)).create(item);
	}
	@Test
	public void itemsServicesRead() {
		itemsServices.readAll();
		Mockito.verify(itemDao, Mockito.times(1)).readAll();
	}
	@Test
	public void itemsServicesUpdate() {
		Item item = new Item("milk", 5.99, 17L);
		itemsServices.update(1L, item);
		Mockito.verify(itemDao, Mockito.times(1)).update(1L,item);
	}
	@Test
	public void itemsServicesDelete() {
		Item item = new Item("milk", 5.99, 17L);
		itemsServices.delete(item);
		Mockito.verify(itemDao, Mockito.times(1)).delete(item);
	}
}