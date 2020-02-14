package com.qa.persistence.domain;

public class Order {
	private Long id;
	private Double price;
	private Long custId;

	public Order(Long id) {
		this.id=id;
		
	}
	public Order(Double price,Long custId) {
		this.price=price;
		this.custId=custId;
		
	}

	public Order(Long id, Long custId, Double price) {
		this.id=id;
		this.price=price;
		this.custId=custId;
	}

    
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String toString() {
		return "\n"+ "OrderId: " + id + "\n"+ "CustomerId: " + custId +"\n"+ "Price " + price+"\n"+ "...................................................." ;
	}

}
