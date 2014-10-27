package com.lab.vo;

public class Product {
	private int itemId;
	private String itemTitle;
	private int itemPrice;
	private int itemImageId;
	private byte[] itemImage;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemImageId() {
		return itemImageId;
	}

	public void setItemImageId(int itemImageId) {
		this.itemImageId = itemImageId;
	}

	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}

}
