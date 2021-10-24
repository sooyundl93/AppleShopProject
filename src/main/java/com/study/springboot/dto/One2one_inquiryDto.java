package com.study.springboot.dto;

import java.util.Date;

// 1:1 문의 글 one2one_inquiry 테이블 Dto

public class One2one_inquiryDto {
	
	private int    inquiry_no;		  // 글 식별변호
	private String user_id;			  // 작성자 ID
	private String inquiry_title;	  // 제목
	private String inquiry_content;	  // 내용
	private Date   inquiry_date;	  // 작성날짜
	private int    number_of_replies; // 달린 답글 개수
	
	public One2one_inquiryDto() {
		super();
	}

	public One2one_inquiryDto(int inquiry_no, String user_id, String inquiry_title, String inquiry_content,
			Date inquiry_date, int number_of_replies) {
		super();
		this.inquiry_no = inquiry_no;
		this.user_id = user_id;
		this.inquiry_title = inquiry_title;
		this.inquiry_content = inquiry_content;
		this.inquiry_date = inquiry_date;
		this.number_of_replies = number_of_replies;
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
