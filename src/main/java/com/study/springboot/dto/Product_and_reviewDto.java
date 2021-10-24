package com.study.springboot.dto;

// 상품 정보 product_info와 member_review 조인 테이블 Dto

//*****************************************************************
//                                                작업자 : 김남권 *
//*****************************************************************


public class Product_and_reviewDto {
	
	private float average_review_score; // 평균 별점
	private int   count_of_review;		// 리뷰 횟수
	
	public Product_and_reviewDto() {
		super();
	}

	public Product_and_reviewDto(float average_review_score, int count_of_review) {
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

}