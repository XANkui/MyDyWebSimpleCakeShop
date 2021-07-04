package com.xan.model;

public class OrderItem {
	private int id;
	private float price;
	private int amount;
	private String goodsName;
	private Goods goods;
	private Order order;	// order_id
	public OrderItem() {
		super();
	}
	
	
	
	public OrderItem(int id, float price, int amount, String goodsName, Goods goods, Order order) {
		super();
		this.id = id;
		this.price = price;
		this.amount = amount;
		this.goodsName = goodsName;
		this.goods = goods;
		this.order = order;
	}



	public OrderItem(float price, int amount, Goods goods, Order order) {
		super();
		this.price = price;
		this.amount = amount;
		this.goods = goods;
		this.order = order;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
		
	// 这是 "select i.id,i.price,i.amount,g.name from orderitem i,goods g where i.order_id=? and i.goods_id=g.id";
	// sql 查询赋值关键 g.name
	public void setName(String name) {
		this.goodsName=name;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String name) {
		this.goodsName = name;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", price=" + price + ", amount=" + amount + ", name=" + goodsName
				+ ", goods=" + goods + ", order=" + order + "]";
	}
	
	
}
