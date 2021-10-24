package com.study.springboot.dto;

import java.util.Date;

// 상품 문의 글 product_inquiry 테이블 Dto

public class Product_inquiryDto {
	
	private int    inquiry_no;		  // 문의글 번호
	private String product_code;	  // 상품번호
	private String user_id;			  // 작성자 ID
	private String inquiry_content;   // 내용
	private String private_flag;	  // 비공개 여부
	private Date   inquiry_date;	  // 작성날짜
	private int    number_of_replies; // 달린 답글 개수
	
	public Product_inquiryDto() {
		super();
	}

	public Product_inquiryDto(int inquiry_no, String product_code, String user_id, String inquiry_content,
			String private_flag, Date inquiry_date, int number_of_replies) {
		super();
		this.inquiry_no = inquiry_no;
		this.product_code = product_code;
		this.user_id = user_id;
		this.inquiry_content = inquiry_content;
		this.private_flag = private_flag;
		this.inquiry_date = inquiry_date;
		this.number_of_replies = number_of_replies;
	}

	public int getInquiry_no() {
		return inquiry_no;
	}

	public void setInquiry_no(int inquiry_no) {
		this.inquiry_no = inquiry_no;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
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
	
}
