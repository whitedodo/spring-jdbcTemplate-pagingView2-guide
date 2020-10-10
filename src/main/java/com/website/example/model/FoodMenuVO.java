package com.website.example.model;

import java.sql.Date;

public class FoodMenuVO {

	private int id;
	private String name;
	private int price;
	private int store_id;
	private int cnt;
	private Date regidate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStore_id() {
		return store_id;
	}
	
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}	
	
}
