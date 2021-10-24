package com.study.springboot.dto;

import java.util.Date;

// 상품 정보 product_info 테이블 Dto

public class Product_infoDto {
	
	private String product_code;		// 상품번호
	private String product_name;		// 상품이름
	private String parent_category;		// 상위 카테고리
	private String child_category;		// 하위 카테고리
	private Date   added_date;			// 추가된 날짜
	private String manufacturer;		// 제조사
	private int    product_price;		// 상품가격
	private String product_description; // 상품설명
	private int    number_of_sales;		// 판매된 수량
	private int    number_of_stock;		// 재고 수량
	private int    shipping_cost;		// 재고 수량
	
	public Product_infoDto() {
		super();
	}

	public Product_infoDto(String product_code, String product_name, String parent_category, String child_category,
			Date added_date, String manufacturer, int product_price, String product_description, int number_of_sales,
			int number_of_stock, int shipping_cost) {
		super();
		this.product_code = product_code;
		this.product_name = product_name;
		this.parent_category = parent_category;
		this.child_category = child_category;
		this.added_date = added_date;
		this.manufacturer = manufacturer;
		this.product_price = product_price;
		this.product_description = product_description;
		this.number_of_sales = number_of_sales;
		this.number_of_stock = number_of_stock;
		this.shipping_cost = shipping_cost;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getParent_category() {
		return parent_category;
	}

	public void setParent_category(String parent_category) {
		this.parent_category = parent_category;
	}

	public String getChild_category() {
		return child_category;
	}

	public void setChild_category(String child_category) {
		this.child_category = child_category;
	}

	public Date getAdded_date() {
		return added_date;
	}

	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getNumber_of_sales() {
		return number_of_sales;
	}

	public void setNumber_of_sales(int number_of_sales) {
		this.number_of_sales = number_of_sales;
	}

	public int getNumber_of_stock() {
		return number_of_stock;
	}

	public void setNumber_of_stock(int number_of_stock) {
		this.number_of_stock = number_of_stock;
	}

	public int getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(int shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

}
