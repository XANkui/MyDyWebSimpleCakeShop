package com.xan.model;

public class Goods {
	private int id;
	private String name;
	private String cover;
	private String image1;
	private String image2;
	private float price;
	private String intro;
	private int stock;
	private Type type;
	
	public Goods() {
		super();
	}

	public Goods(int id, String name, String cover, String image1, String image2, float price, String intro, int stock,
			Type type) {
		super();
		this.id = id;
		this.name = name;
		this.cover = cover;
		this.image1 = image1;
		this.image2 = image2;
		this.price = price;
		this.intro = intro;
		this.stock = stock;
		this.type = type;
	}

	// 查询 BeanHandler 对象的时候会自动调用，注意函数命名
	public void setTypeid(int typeid) {
		System.out.println(typeid);
		if(type==null) {
			
			type = new Type();
		}
		type.setId(typeid);
	}
	
	// 查询 BeanHandler 对象的时候会自动调用，注意函数命名
	public void setTypename(String typename) {
		System.out.println(typename);
		if(type==null) {
			
			type = new Type();
		}
		type.setName(typename);
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
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", cover=" + cover + ", image1=" + image1 + ", image2=" + image2
				+ ", price=" + price + ", intro=" + intro + ", stock=" + stock + ", type=" + type + "]";
	}
	
	
	
	
	
	
	
}
