package com.study.springboot.dto;

// 장바구니 목록 shopping_cart 테이블 Dto

public class Shopping_cartDto {
	
	private int    item_no;			   // 품목 식별번호
	private String user_id;			   // 사용자 ID
	private String product_code;	   // 상품 번호
	private int    number_of_products; // 담은 개수
	
	public Shopping_cartDto() {
		super();
	}

	public Shopping_cartDto(int item_no, String user_id, String product_code, int number_of_products) {
		super();
		this.item_no = item_no;
		this.user_id = user_id;
		this.product_code = product_code;
		this.number_of_products = number_of_products;
	}

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public int getNumber_of_products() {
		return number_of_products;
	}

	public void setNumber_of_products(int number_of_products) {
		this.number_of_products = number_of_products;
	}
			
}
