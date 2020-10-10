package com.website.example.model;

import java.sql.Timestamp;

public class FoodMenuViewVO {
	
	private int rnum;
	private int id;
	private String name;
	private int price;
	private String storename;
	private Timestamp regidate;
	private int cnt;
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

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
	
	public String getStorename() {
		return storename;
	}
	
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	public Timestamp getRegidate() {
		return regidate;
	}

	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

}
