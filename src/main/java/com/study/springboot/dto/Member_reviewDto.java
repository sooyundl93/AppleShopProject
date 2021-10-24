package com.study.springboot.dto;

import java.util.Date;

// 회원 상품 리뷰 member_review 테이블 Dto

public class Member_reviewDto {
	
	private int    post_no;		 // 리뷰 글 식별번호
	private String product_code; // 상품 식별번호
	private String user_id;		 // 작성자 ID
	private float  review_score; // 별점 0.5 ~ 5.0
	private Date   post_date;	 // 작성 날짜
	private String post_content; // 리뷰 내용
	
	public Member_reviewDto() {
		super();
	}

	public Member_reviewDto(int post_no, String product_code, String user_id, float review_score, Date post_date,
			String post_content) {
		super();
		this.post_no = post_no;
		this.product_code = product_code;
		this.user_id = user_id;
		this.review_score = review_score;
		this.post_date = post_date;
		this.post_content = post_content;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
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

	public float getReview_score() {
		return review_score;
	}

	public void setReview_score(float review_score) {
		this.review_score = review_score;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	
}
