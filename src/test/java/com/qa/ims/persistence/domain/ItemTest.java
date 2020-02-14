//package com.qa.ims.persistence.domain;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
////import static org.junit.Assert.assertNotNull;
////import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.qa.persistence.domain.Item;
//public class ItemTest {
//	private Item item;
//	private Item other;
//	@Before
//	public void setUp() {
//		item = new Item("Mug",4.75,1L);
//		other = new Item("Mug",4.75,1L );
//	}
//	@Test
//	public void settersTest() {
//		assertNotNull(item.getId());
//		assertNotNull(item.getName());
//
//		
//		item.setId(null);
//		assertNull(item.getId());
//		item.setName(null);
//		assertNull(item.getName());
//		item.setName(null);
//		assertNull(item.getName());
//		
//	}
//	@Test
//	public void equalsWithNull() {
//		assertFalse(item.equals(null));
//	}
//	@Test
//	public void equalsWithDifferentObject() {
//		assertTrue(item.equals(new Object()));
//	}
//	@Test
//	public void createitemsWithId() {
//		assertEquals(1L, item.getId(), 0);
//		assertEquals("Mug", item.getName());
//		assertEquals(4.75, item.getPrice(),0);
//	}
//	@Test
//	public void checkEquality() {
//		assertTrue(item.equals(item));
//	}
//	@Test
//	public void checkEqualityBetweenDifferentObjects() {
//		assertTrue(item.equals(other));
//	}
//	@Test
//	public void itemsNameNullButOtherNameNotNull() {
//		item.setName(null);
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void itemsNamesNotEqual() {
//		other.setName("");
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void checkEqualityBetweenDifferentObjectsitem_name() {
//		item.setName("milk");
//		other.setName("milk");
//		assertTrue(item.getName().equals(other.getName()));
//	}
//	@Test
//	public void nullId() {
//		item.setId((long)0);
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void nullIdOnBoth() {
//		item.setId((long)0);
//		other.setId((long)0);
//		assertTrue(item.equals(other));
//	}
//	@Test
//	public void otherIdDifferent() {
//		other.setId(2L);
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void nullItem_name() {
//		item.setName(null);
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void nullItem_nameOnBoth() {
//		item.setName(null);
//		other.setName(null);
//		assertTrue(item.getName().equals(other.getName()));
//	}
//	
//	@Test
//	public void otherItem_nameDifferent() {
//		other.setName("Haggis");
//		assertFalse(item.equals(other));
//	}
//	@Test
//	public void constructorWithoutId() {
//		Item items = new Item("Mug",4.75,1L);
//		assertNull(items.getId());
//		assertNotNull(item.getName());
//		assertNotNull(item.getPrice());
//	}
//	@Test
//	public void hashCodeTest() {
//		assertEquals(item.hashCode(), other.hashCode());
//	}
//	@Test
//	public void hashCodeTestWithNull() {
//		Item items = new Item(null, null, null);
//		Item other = new Item(null, null, null);
//		assertEquals(items.hashCode(), other.hashCode());
//	}
//	@Test
//	public void toStringTest() {
//		String toString = "\n"+ "ItemId: 1"  + "\n"+ "ItemName: milk" +"\n"+ "Price: 5.99" +"\n"+ "Quantity: 17"+"\n"+ "...................................................." ;
//		assertEquals(toString, item.toString());
//	}
//}