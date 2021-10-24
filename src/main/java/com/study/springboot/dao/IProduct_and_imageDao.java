package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.Product_and_imageDto;

// 상품 정보 product_info 테이블 Dao

@Mapper
public interface IProduct_and_imageDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 입고 최신순 8항목 조회
	public ArrayList<Product_and_imageDto> new8List();
	
	// 판매량 상위 5항목 조회
	public ArrayList<Product_and_imageDto> top5List();
	
}