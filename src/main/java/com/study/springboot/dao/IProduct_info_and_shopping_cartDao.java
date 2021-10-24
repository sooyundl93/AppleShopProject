package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Product_info_and_shopping_cartDto;

// 주문결제폼 product_info AND shopping_cart AND product_image_info 테이블 조인 Dao

@Mapper
public interface IProduct_info_and_shopping_cartDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 주문 상품정보 조회
	public ArrayList<Product_info_and_shopping_cartDto> user_cart_list(@Param("user_id") String user_id );
	public Product_info_and_shopping_cartDto for_shipping_cost(@Param("user_id") String user_id,
															   @Param("product_code") String product_code);
	
}