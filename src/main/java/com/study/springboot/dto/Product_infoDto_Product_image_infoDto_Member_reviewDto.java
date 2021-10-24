package com.study.springboot.dto;

import java.util.Date;

// 상품 정보 product_info 테이블 Dto

//*****************************************************************
//                                                작업자 : 윤대호 *
//*****************************************************************

public class Product_infoDto_Product_image_infoDto_Member_reviewDto {
	
	
	public Product_infoDto_Product_image_infoDto_Member_reviewDto() {
		super();
	}
	
	public Product_infoDto_Product_image_infoDto_Member_reviewDto(String product_code, String product_name,
			String parent_category, String child_category, Date added_date, String manufacturer, int product_price,
			String product_description, int number_of_sales, int image_no, String image_file_name, int post_no,
			String user_id, float review_score, Date post_date, String post_content) {
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
		this.image_no = image_no;
		this.image_file_name = image_file_name;
		this.post_no = post_no;
		this.user_id = user_id;
		this.review_score = review_score;
		this.post_date = post_date;
		this.post_content = post_content;
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
	
	public int getPost_no() {
		return post_no;
	}
	
	public void setPost_no(int post_no) {
		this.post_no = post_no;
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
	
	private String product_code;		// 상품번호
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
	
	private int    post_no;		 // 리뷰 글 식별번호
	
	private String user_id;		 // 작성자 ID
	private float  review_score; // 별점 0.5 ~ 5.0
	private Date   post_date;	 // 작성 날짜
	private String post_content; // 리뷰 내용
	private float average_review_score; // 평균 별점
	
	public Product_infoDto_Product_image_infoDto_Member_reviewDto(float average_review_score, int count_of_review) {
		super();
		this.average_review_score = average_review_score;
		this.count_of_review = count_of_review;
	}
	
	public float getAverage_review_score() {
		return average_review_score;
	}
	
	public void setAverage_review_score(float average_review_score) {
		this.average_review_score = average_review_score;
	}
	
	public int getCount_of_review() {
		return count_of_review;
	}
	
	public void setCount_of_review(int count_of_review) {
		this.count_of_review = count_of_review;
	}
	
	private int   count_of_review;		// 리뷰 횟수
	
}
