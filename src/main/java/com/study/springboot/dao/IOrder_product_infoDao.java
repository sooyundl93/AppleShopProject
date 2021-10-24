package com.study.springboot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.Order_product_infoDto;

// 주문 상품 정보 order_product_info 테이블 Dao

@Mapper
public interface IOrder_product_infoDao {
	
	//*****************************************************************
	//                                                작업자 : 김남권 *
	//*****************************************************************
	
	// 주문 상품 정보 INSERT
	public int add_order_product_info(@Param("order_id")         String order_id,
									  @Param("product_code")     String product_code,
									  @Param("product_name")     String product_name, 
									  @Param("parent_category")  String parent_category,
									  @Param("child_category")   String child_category, 
									  @Param("manufacturer")     String manufacturer, 
									  @Param("product_price")    int product_price,
									  @Param("number_of_orders") int number_of_orders, 
									  @Param("shipping_cost")    int shipping_cost
									 );
	
	// 주문 상품 정보 조회
	public ArrayList<Order_product_infoDto> user_order_list(@Param("order_id") String order_id);
	
	//*****************************************************************
	//                                                작업자 : 윤대호 *
	//*****************************************************************
	
	// 리뷰 작성 여부 "Y" UPDATE
	public void wrote_review_flag(String product_code, String order_id);
		
}