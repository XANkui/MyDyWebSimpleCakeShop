package com.xan.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xan.utils.PriceUtil;

public class Order {
	private int id;
	private float total;	// 总价
	private int amount;		// 总数量
	private int status;		// 1 未付款 ；2 已付款；3 已发货；4 已完成
	private int paytype;	// 1 微信； 2 支付宝； 3 货到付款
	private String name;
	private String phone;
	private String address;
	//private Date datetime;	// 这个会报错 cannot localdatetime covert to date
	private LocalDateTime datetime; // 使用这个
	private User user;
	
	
	private Map<Integer, OrderItem> itemMap = new HashMap<>();
	private List<OrderItem> itemList = new ArrayList<OrderItem>();
	
	
	public Order() {
		super();
	}
	public Order(int id, float total, int amount, int status, int paytype, String name, String phone, String address,
			LocalDateTime datetime, User user) {
		super();
		this.id = id;
		this.total = total;
		this.amount = amount;
		this.status = status;
		this.paytype = paytype;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.datetime = datetime;
		this.user = user;
	}
	
	public void AddGoods(Goods goods) {
		
		if(itemMap.containsKey(goods.getId())) {
			
			OrderItem item = itemMap.get(goods.getId());
			item.setAmount(item.getAmount()+1);
			
		}else {
			
			OrderItem item = new OrderItem(goods.getPrice(),1,goods,this);
			itemMap.put(goods.getId(), item);
		}
		
		amount++;
		//total+=goods.getPrice();	// 可能有精度问题
		total = PriceUtil.add(total, goods.getPrice());
	}
	
	public void lessen(int goodsId) {
		if(itemMap.containsKey(goodsId)) {
			OrderItem item = itemMap.get(goodsId);
			item.setAmount(item.getAmount()-1);
			amount--;
			//total -= item.getPrice(); // 可能有精度问题
			total = PriceUtil.subtract(total, item.getPrice());
			
			if(item.getAmount()<=0) {
				itemMap.remove(goodsId);
			}
		}
	}
	
	public void setUsername(String username) {
		user = new User();
		user.setUsername(username);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<Integer, OrderItem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<Integer, OrderItem> itemMap) {
		this.itemMap = itemMap;
	}
	public List<OrderItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", amount=" + amount + ", status=" + status + ", paytype="
				+ paytype + ", name=" + name + ", phone=" + phone + ", address=" + address + ", datetime=" + datetime
				+ ", user=" + user + ", itemMap=" + itemMap + ", itemList=" + itemList + "]";
	}
	
}
