package com.study.springboot.dto;

import java.util.Date;

// 상품 문의 product_inquiry AND product_inquiry_reply 조인 테이블 Dto

//*****************************************************************
//                                                작업자 : 김남권 *
//*****************************************************************

public class Product_inquiry_and_replyDto {
	
	private String user_id;			// 질문 작성자 ID
	private String inquiry_content; // 문의글 내용
	private String private_flag;	// 비밀글 여부
	private Date   inquiry_date;	// 질문 작성날짜
	private String admin_id;		// 답변자 ID
	private String reply_content;	// 답변 내용
	private Date   reply_date;		// 답변 날짜
	
	public Product_inquiry_and_replyDto() {
		super();
	}

	public Product_inquiry_and_replyDto(String user_id, String inquiry_content, String private_flag, Date inquiry_date,
			String admin_id, String reply_content, Date reply_date) {
		super();
		this.user_id = user_id;
		this.inquiry_content = inquiry_content;
		this.private_flag = private_flag;
		this.inquiry_date = inquiry_date;
		this.admin_id = admin_id;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
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

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
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
