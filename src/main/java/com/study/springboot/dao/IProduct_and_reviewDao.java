package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Product_and_reviewDto;

// 상품 정보 product_info AND member_review 조인 테이블 Dao

@Mapper
public interface IProduct_and_reviewDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 입고 최신순 리뷰 평균 조회
	public ArrayList<Product_and_reviewDto> new8ReviewList();
	
	// 상품 단건 리뷰 개수 & 점수 평균 조회
	public Product_and_reviewDto product_score(String product_code);
	
}