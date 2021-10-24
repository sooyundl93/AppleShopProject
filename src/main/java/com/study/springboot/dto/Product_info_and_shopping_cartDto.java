package com.study.springboot.dto;

// 주문결제폼 product_info AND shopping_cart AND product_image_info 테이블 조인 Dto

//*****************************************************************
//                                                작업자 : 김남권 *
//*****************************************************************

public class Product_info_and_shopping_cartDto {
	
	private String product_code;   	      // 상품 코드
	private String product_name;   	      // 상품 이름
	private int    product_price;         // 상품 가격
	private String user_id;			      // 주문자 ID
	private int    number_of_products;    // 주문 개수
	private String image_file_name;       // 이미지 파일명
	private int    product_total_price;   // 상품 가격 * 주문 개수  
	private int	   product_shipping_cost; // 총 배송비
	
	public Product_info_and_shopping_cartDto() {
		super();
	}

	public Product_info_and_shopping_cartDto(String product_code, String product_name, int product_price,
			String user_id, int number_of_products, String image_file_name, int product_total_price,
			int product_shipping_cost) {
		super();
		this.product_code = product_code;
		this.product_name = product_name;
		this.product_price = product_price;
		this.user_id = user_id;
		this.number_of_products = number_of_products;
		this.image_file_name = image_file_name;
		this.product_total_price = product_total_price;
		this.product_shipping_cost = product_shipping_cost;
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

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getNumber_of_products() {
		return number_of_products;
	}

	public void setNumber_of_products(int number_of_products) {
		this.number_of_products = number_of_products;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}

	public int getProduct_total_price() {
		return product_total_price;
	}

	public void setProduct_total_price(int product_total_price) {
		this.product_total_price = product_total_price;
	}

	public int getProduct_shipping_cost() {
		return product_shipping_cost;
	}

	public void setProduct_shipping_cost(int product_shipping_cost) {
		this.product_shipping_cost = product_shipping_cost;
	}

}
