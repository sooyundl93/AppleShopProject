package com.study.springboot.dto;

import java.util.Date;

// 상품 문의 답변 글 product_inquiry_reply 테이블 Dto

public class Product_inquiry_replyDto {
	
	private int    reply_no;	  // 답글 번호
	private int    inquiry_no;	  // 문의글 번호
	private String user_id;		  // 작성자(관리자) ID
	private String reply_content; // 내용
	private Date   reply_date;	  // 작성날짜
	
	public Product_inquiry_replyDto() {
		super();
	}

	public Product_inquiry_replyDto(int reply_no, int inquiry_no, String user_id, String reply_content,
			Date reply_date) {
		super();
		this.reply_no = reply_no;
		this.inquiry_no = inquiry_no;
		this.user_id = user_id;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
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

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
			
}
