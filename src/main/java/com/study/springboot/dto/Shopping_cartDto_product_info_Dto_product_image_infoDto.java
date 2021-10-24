package com.study.springboot.dto;

import java.util.Date;

// 장바구니 목록 shopping_cart 테이블 Dto

//*****************************************************************
//                                                작업자 : 윤대호 *
//*****************************************************************

public class Shopping_cartDto_product_info_Dto_product_image_infoDto {

	private int    item_no;			   // 품목 식별번호
	private String user_id;			   // 사용자 ID
	private String product_code;	   // 상품 번호
	private int    number_of_products; // 담은 개수
	private String product_name;		// 상품이름
	private String parent_category;		// 상위 카테고리
	private String child_category;		// 하위 카테고리
	private Date   added_date;			// 추가된 날짜
	private String manufacturer;		// 제조사
	private int    product_price;		// 상품가격
	private String product_description; // 상품설명
	private int    number_of_sales;		// 판매된 수량
	private int    image_no;		// 이미지 식별번호
	private String image_file_name; // 이미지 파일 명
	private int    shipping_cost;		// 배송비
	
	public Shopping_cartDto_product_info_Dto_product_image_infoDto() {
		super();
	}

	public Shopping_cartDto_product_info_Dto_product_image_infoDto(int item_no, String user_id, String product_code,
			int number_of_products, String product_name, String parent_category, String child_category, Date added_date,
			String manufacturer, int product_price, String product_description, int number_of_sales, int image_no,
			String image_file_name, int shipping_cost) {
		super();
		this.item_no = item_no;
		this.user_id = user_id;
		this.product_code = product_code;
		this.number_of_products = number_of_products;
		this.product_name = product_name;
		this.parent_category = parent_category;
		this.child_category = child_category;
		this.added_date = added_date;
		this.manufacturer = manufacturer;
		this.product_price = product_price;
		this.product_description = product_description;
		this.number_of_sales = number_of_sales;
		this.image_no = image_no;
		this.image_file_name = image_file_name;
		this.shipping_cost = shipping_cost;
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

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}

	public int getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(int shipping_cost) {
		this.shipping_cost = shipping_cost;
	}
	
}
