package com.study.springboot.dto;

import java.util.Date;

// 주문 정보 order_info 테이블 Dto

//*****************************************************************
//                                                작업자 : 윤대호 *
//*****************************************************************

public class product_info_product_inquiryDto {
	
	private String product_code;		// 상품번호
	public product_info_product_inquiryDto() {
		super();
	}
	public product_info_product_inquiryDto(String product_code, String product_name, String parent_category,
			String child_category, Date added_date, String manufacturer, int product_price, String product_description,
			int number_of_sales, int inquiry_no, String user_id, String inquiry_content, String private_flag,
			Date inquiry_date, int number_of_replies) {
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
		this.inquiry_no = inquiry_no;
		this.user_id = user_id;
		this.inquiry_content = inquiry_content;
		this.private_flag = private_flag;
		this.inquiry_date = inquiry_date;
		this.number_of_replies = number_of_replies;
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
	public int getInquiry_no() {
		return inquiry_no;
	}
	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInquiry_content() {
		return inquiry_content;
	}
	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}
	public String getPrivate_flag() {
		return private_flag;
	}
	public void setPrivate_flag(String private_flag) {
		this.private_flag = private_flag;
	}
	public Date getInquiry_date() {
		return inquiry_date;
	}
	public void setInquiry_date(Date inquiry_date) {
		this.inquiry_date = inquiry_date;
	}
	public int getNumber_of_replies() {
		return number_of_replies;
	}
	public void setNumber_of_replies(int number_of_replies) {
		this.number_of_replies = number_of_replies;
	}
	private String product_name;		// 상품이름
	private String parent_category;		// 상위 카테고리
	private String child_category;		// 하위 카테고리
	private Date   added_date;			// 추가된 날짜
	private String manufacturer;		// 제조사
	private int    product_price;		// 상품가격
	private String product_description; // 상품설명
	private int    number_of_sales;		// 판매된 수량
	
	private int    inquiry_no;		  // 문의글 번호
	
	private String user_id;			  // 작성자 ID
	private String inquiry_content;   // 내용
	private String private_flag;	  // 비공개 여부
	private Date   inquiry_date;	  // 작성날짜
	private int    number_of_replies; // 달린 답글 개수
	
		
}
