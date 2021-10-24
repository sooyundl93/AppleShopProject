package com.study.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberReviewDto {
	private String user_id;
	private int post_no;
	private String product_code;
	private String product_name;
	private float review_score;
	private Date post_date;
	private String post_content;
	private ArrayList<String> imageFileList;
	private SimpleDateFormat detailedTimeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); // 연월일 시간 포맷	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getReview_score() {
		return review_score;
	}
	public void setReview_score(float review_score) {
		this.review_score = review_score;
	}
	public String getPost_date() {
		return detailedTimeFormat.format(post_date);
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
	public ArrayList<String> getImageFileList() {
		return imageFileList;
	}
	public void setImageFileList(ArrayList<String> imageFileList) {
		this.imageFileList = imageFileList;
	}
}
