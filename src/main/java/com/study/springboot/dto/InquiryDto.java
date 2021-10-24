package com.study.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InquiryDto {
	private int inquiry_no;			// 문의 글 식별번호
	private String product_code;	// 상품 코드 (상품 문의 글에 필요)
	private String product_name;	// 상품명 (상품 문의 글에 필요)
	private String user_id;			// 사용자 아이디
	private String inquiry_title;	// 문의 제목 (1:1 문의 글에 필요)
	private String inquiry_content;	// 문의 내용
	private String private_flag;	// 비공개 글 여부 (상품 문의 글에 필요)
	private Date inquiry_date;		// 문의 작성 날짜
	private int number_of_replies;	// 답글 받은 횟수
	
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷	
	
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
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
	public String getInquiry_date() {
		return detailedTimeFormat.format(inquiry_date);
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
