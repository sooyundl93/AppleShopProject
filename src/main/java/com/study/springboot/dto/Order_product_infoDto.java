package com.study.springboot.dto;

// 주문 상품 정보 order_product_info 테이블 Dto

public class Order_product_infoDto {
	
	private int    item_no;			  // 주문 품목 식별번호
	private String order_id;		  // 주문번호
	private String product_code;	  // 상품번호
	private String product_name;	  // 주문한 상품명
	private String parent_category;   // 상위 카테고리
	private String child_category;    // 하위 카테고리
	private String manufacturer;	  // 제조사
	private int    product_price;	  // 주문한 상품 가격
	private int    shipping_cost;	  // 배송비
	private int    number_of_orders;  // 주문한 개수
	private String wrote_review_flag; // 리뷰 작성 여부
	
	public Order_product_infoDto() {
		super();
	}

	public Order_product_infoDto(int item_no, String order_id, String product_code, String product_name,
			String parent_category, String child_category, String manufacturer, int product_price, int shipping_cost,
			int number_of_orders, String wrote_review_flag) {
		super();
		this.item_no = item_no;
		this.order_id = order_id;
		this.product_code = product_code;
		this.product_name = product_name;
		this.parent_category = parent_category;
		this.child_category = child_category;
		this.manufacturer = manufacturer;
		this.product_price = product_price;
		this.shipping_cost = shipping_cost;
		this.number_of_orders = number_of_orders;
		this.wrote_review_flag = wrote_review_flag;
	}

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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

	public int getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(int shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public int getNumber_of_orders() {
		return number_of_orders;
	}

	public void setNumber_of_orders(int number_of_orders) {
		this.number_of_orders = number_of_orders;
	}

	public String getWrote_review_flag() {
		return wrote_review_flag;
	}

	public void setWrote_review_flag(String wrote_review_flag) {
		this.wrote_review_flag = wrote_review_flag;
	}

}
