package com.qa.persistence.domain;

public class OrderLine {
	private long id;
	private long orderId;
	private long itemId;
	private int quantity;
	public OrderLine(long orderId, long itemId, int quantity) {
		this.orderId=orderId;
		this.itemId=itemId;
		this.quantity=quantity;
	}
	public OrderLine(long id, long orderId, long itemId, int quantity) {
		this.id=id;
		this.orderId=orderId;
		this.itemId=itemId;
		this.quantity=quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return "\n"+ "OrderlineId "+id+"\n"+ "OrderId " + orderId + "\n"+ "ItemId: " + itemId +"\n"+ "quantity: " + quantity+"\n"+ "...................................................." ;
	}
	
}
