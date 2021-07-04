package com.xan.model;

public class Recommend {
	private int id;
	private int type;
	private int goods_id;
	public Recommend() {
		super();
	}
	public Recommend(int id, int type, int goods_id) {
		super();
		this.id = id;
		this.type = type;
		this.goods_id = goods_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	
}
