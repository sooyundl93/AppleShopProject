package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_image_infoDto;

// 상품 이미지 정보 product_image_info 테이블 Dao

@Mapper
public interface IProduct_image_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 상품 이미지 조회
	public ArrayList<Product_image_infoDto> product_image(String product_code);
	
	// 상품 이미지 1장 조회
	public Product_image_infoDto product_image_one(String product_code);
	
	// 주문 완료 폼 이미지 조회
	public ArrayList<Product_image_infoDto> order_success_image(@Param("query") String query);
	
	// 상품 이미지 추가
	public int add_product_image(@Param("product_code") String product_code,
								 @Param("image_file_name") String image_file_name);
	
	// 상품 이미지 업데이트를 위한 이미지 번호 GET
	public ArrayList<Integer> get_image_no(@Param("product_code") String product_code);
	
	// 상품 이미지 업데이트
	public int update_product_image(@Param("image_no") int image_no,
									@Param("image_file_name") String image_file_name);
	
}