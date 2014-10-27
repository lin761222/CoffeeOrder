package com.lab.vo;

public class Order {
	private int oId;
	private int oItemId;
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public int getoItemId() {
		return oItemId;
	}
	public void setoItemId(int oItemId) {
		this.oItemId = oItemId;
	}
	
	
}
