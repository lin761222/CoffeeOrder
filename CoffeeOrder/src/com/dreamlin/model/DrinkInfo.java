package com.dreamlin.model;

public class DrinkInfo {
	String name;
	String price;
	int number;

	public DrinkInfo(String name, String price) {
		this.price = price;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
