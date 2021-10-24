package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_infoDto;

// 상품 정보 product_info 테이블 Dao

@Mapper
public interface IProduct_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 상품 단건 상세조회
	public Product_infoDto product_info(@Param("product_code") String product_code);
	
	// 상품 판매개수 +1 UPDATE
	public int update_number_of_sales(@Param("product_code") String product_code,
									  @Param("number_of_sales") int number_of_sales);
	
	// 마지막 추가 상품코드 조회
	public String get_last_productCode();
}